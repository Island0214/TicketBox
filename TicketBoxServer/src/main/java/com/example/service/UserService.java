package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by island on 2018/3/14.
 */
@Service
public interface UserService {
    Map<Integer, User> logIn(String username, String password);
}
