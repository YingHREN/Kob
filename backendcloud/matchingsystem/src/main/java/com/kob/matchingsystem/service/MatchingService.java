package com.kob.matchingsystem.service;

public interface MatchingService {
    String addPlayer(Integer userid, Integer rating);
    String removePlayer(Integer userId);
}
