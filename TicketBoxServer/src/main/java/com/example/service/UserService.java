package com.example.service;

import org.springframework.stereotype.Service;

/**
 * Created by island on 2018/3/14.
 */
@Service
public interface UserService {
    public boolean logIn(String username, String password);
}
