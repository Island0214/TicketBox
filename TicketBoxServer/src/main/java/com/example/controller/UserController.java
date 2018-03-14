package com.example.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by island on 2018/3/14.
 */
@RestController
public class UserController {
    @RequestMapping("/")
    String home() {
        return "welcome";
    }
}
