package com.kob.backend.service.impl.user.bot;

import com.kob.backend.Pojo.Bot;
import com.kob.backend.Pojo.User;
import com.kob.backend.mapper.Botmapper;
import com.kob.backend.service.impl.util.UserDetailsImpl;
import com.kob.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private Botmapper botmapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");
//        System.out.println(title);
        Map<String, String>map = new HashMap<>();
        if(title==null||title.length()==0){
            map.put("error_message", "title can not be null");
            return map;
        }

        if(title.length()>100){
            map.put("error_message", "Title length can not be bigger than 100");
            return map;
        }

        if(description==null||description.length() == 0){
            map.put("error_message", "user is lazy, nothing left");
        }

        if(description.length() > 300){
            map.put("error_message", "Bot description length can not bigger than 300");
            return map;
        }

        if(content == null||content.length()==0){
            map.put("error_message", "content length can not be empty");
            return map;
        }

        if(content.length()>100){
            map.put("error_message", "content can not be bigger than 100");
            return map;
        }


        Bot bot = botmapper.selectById(bot_id);
        if(bot==null){
            map.put("error_message","Bot not exits or deleted");
            return map;
        }

        if(!bot.getUserId().equals(bot_id)){
            map.put("error_message","No rights to modify the bot");
            return map;
        }

        Bot new_bot = new Bot(
                bot.getId(),
                user.getId(),
                title,
                description,
                content,
                bot.getRating(),
                bot.getCreatetime(),
                new Date()
        );
        botmapper.updateById(new_bot);
        map.put("error_message", "success");

        return map;
    }
}
