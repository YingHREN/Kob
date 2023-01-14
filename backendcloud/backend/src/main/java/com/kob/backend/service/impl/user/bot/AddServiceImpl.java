package com.kob.backend.service.impl.user.bot;

import com.kob.backend.Pojo.Bot;
import com.kob.backend.Pojo.User;
import com.kob.backend.mapper.Botmapper;
import com.kob.backend.service.impl.util.UserDetailsImpl;
import com.kob.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {
    @Autowired
    private Botmapper botMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");
        System.out.println(title);
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

        Date now = new Date();
        Bot bot = new Bot(null, user.getId(), title, description, content, now, now);

        botMapper.insert(bot);
        map.put("error_message", "success");

        return map;
    }
}
