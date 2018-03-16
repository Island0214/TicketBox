package com.example.model;

/**
 * Created by island on 2018/3/16.
 */
public class VenueRegisterBean {
    private String address;

    private String name;

    private String password;

    public VenueRegisterBean() {
    }

    public VenueRegisterBean(String address, String name, String password) {
        this.address = address;
        this.name = name;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
