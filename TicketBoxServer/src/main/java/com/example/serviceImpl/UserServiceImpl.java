package com.example.serviceImpl;

import com.example.dao.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by island on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean logIn(String username, String password) {
//        List<User> users = userDao.findByLastname(username);
//        if (users.size() > 0) {
//            return users.get(0).getPassword().equals(password);
//        } else {
            return false;
//        }
    }
}
