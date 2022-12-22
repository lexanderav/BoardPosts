package com.example.boardposts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BoardPostsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BoardPostsApplication.class, args);
        //PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
        //System.out.println(passwordEncoder.encode("admin"));
    }

}
