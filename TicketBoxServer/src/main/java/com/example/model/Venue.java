package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/16.
 */
@Entity
public class Venue {
    @Id
    private int code;

    private String name;

    private String password;

    private int status;

    private String address;

    public Venue() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
