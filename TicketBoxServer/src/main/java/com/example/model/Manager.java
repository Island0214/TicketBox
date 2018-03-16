package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/16.
 */
@Entity
public class Manager {
    @Id
    private int manager_id;

    private String username;

    private String password;

    public Manager() {
    }

    public Manager(int manager_id, String username, String password) {
        this.manager_id = manager_id;
        this.username = username;
        this.password = password;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
