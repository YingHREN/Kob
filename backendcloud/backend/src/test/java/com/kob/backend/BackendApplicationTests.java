package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordencoder  = new BCryptPasswordEncoder();
        System.out.println(passwordencoder.encode("Top"));
        System.out.println(passwordencoder.encode("Top"));
        System.out.println();
    }

}
