package com.example.serviceImpl;

import com.example.dao.UserRepository;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by island on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<Integer, User> logIn(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        Map<Integer, User> map = new HashMap<>();

        if (user != null) {
            map.put(user.getStatus(), user);
        } else {
            map.put(3, null);
        }

        return map;

    }
}
