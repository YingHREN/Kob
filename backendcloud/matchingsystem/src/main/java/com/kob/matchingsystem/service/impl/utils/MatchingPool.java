package com.kob.matchingsystem.service.impl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MatchingPool extends Thread{
    private static List<Player> players = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private static RestTemplate restTemplate;

    private final static String startGameUrl = "http://127.0.0.1:3000/pk/start/game/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        MatchingPool.restTemplate = restTemplate;
    }

    public void addPlayer(Integer userId, Integer rating){
        lock.lock();
        try{
            players.add(new Player(userId, rating, 0));
        }finally {
            lock.unlock();
        }
    }

    public void removePlayer(Integer userId){
        lock.lock();
        try{
            List<Player> newPlayers = new ArrayList<>();
            for(Player player:players){
                if(!player.getUserId().equals(userId)){
                    newPlayers.add(player);
                }
            }
            players = newPlayers;
        }finally {
            lock.unlock();
        }
    }
    private boolean checkMatched(Player a, Player b){
        int ratingDelta = Math.abs(a.getRating() - b.getRating());
        int waitingtime = Math.min(a.getWaitigtime(), b.getWaitigtime());
        return ratingDelta <=waitingtime * 10;
    }

    private void sendResult(Player a, Player b){
        System.out.println("send Result: "+ a + " " + b);
        MultiValueMap<String, String>data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("b_id", b.getUserId().toString());
        restTemplate.postForObject(startGameUrl, data, String.class);
    }

    private void matchPlayers(){ //try to match all the players
        boolean[] used = new boolean[players.size()];
        System.out.println("match players: "+players.toString());
        for(int i=0; i<players.size(); i++){
            if(used[i]) continue;
            for(int j = i + 1; j<players.size(); j++){
                if(used[j])continue;
                Player a = players.get(i), b = players.get(j);
                if(checkMatched(a, b)){
                    used[i] = used[j] = true;
                    sendResult(a, b);
                    break;
                }
            }
        }
        List<Player> newplayers  = new ArrayList<>();
        for(int i=0; i<players.size(); i++){
            if(!used[i]){newplayers.add(players.get(i));}
        }
        players = newplayers;
    }
    private void increasingWaitingTime(){
        for(Player player:players){
            player.setWaitigtime(player.getWaitigtime()+1);
        }
    }
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(100);
                lock.lock();
                try{
                    increasingWaitingTime();
                    matchPlayers();
                }finally {
                    lock.unlock();
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
