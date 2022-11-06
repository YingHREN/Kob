package com.kob.backend.controller.user.account;


import com.kob.backend.service.user.account.infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private infoService infoservice;

    @GetMapping("/user/account/info/")
    public Map<String, String> getinfo(){
        return infoservice.getinfo();
    }
}