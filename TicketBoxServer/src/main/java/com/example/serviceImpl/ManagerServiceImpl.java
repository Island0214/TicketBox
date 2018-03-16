package com.example.serviceImpl;

import com.example.dao.ManagerRepository;
import com.example.model.Manager;
import com.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by island on 2018/3/16.
 */
@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public boolean login(String username, String password) {
        Manager manager = managerRepository.findByUsernameAndPassword(username, password);

        if (manager != null) {
            return true;
        } else {
            return false;
        }
    }
}
