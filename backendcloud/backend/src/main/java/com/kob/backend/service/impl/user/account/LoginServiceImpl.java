package com.kob.backend.service.impl.user.account;

import com.kob.backend.Pojo.User;
import com.kob.backend.Utils.JwtUtil;
import com.kob.backend.service.impl.util.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//如果登陆失败会自动处理
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String Jwt = JwtUtil.createJWT(user.getId().toString());
        Map<String,String>map = new HashMap<>();
        map.put("error_message", "success");
        map.put("token", Jwt);
        return map;
    }
}
