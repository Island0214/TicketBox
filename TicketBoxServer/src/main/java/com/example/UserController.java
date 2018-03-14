package com.example;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by island on 2018/3/14.
 */
@RestController
@EnableJpaRepositories
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String hello() {
        return "asd";
    }

    @RequestMapping("/login")
    boolean login() {
        return userService.logIn("island", "123456");
    }

    @RequestMapping("/signIn")
    boolean signIn() {
        return userService.logIn(",", ",");
    }
}
