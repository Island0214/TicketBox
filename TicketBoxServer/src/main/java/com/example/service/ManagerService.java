package com.example.service;

import org.springframework.stereotype.Service;

/**
 * Created by island on 2018/3/16.
 */
@Service
public interface ManagerService {
    boolean login(String username, String password);
}
