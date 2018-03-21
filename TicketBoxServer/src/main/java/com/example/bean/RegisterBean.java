package com.example.bean;

/**
 * Created by island on 2018/3/15.
 */
public class RegisterBean {
    String username;
    String email;
    String password;

    public RegisterBean() {
    }

    public RegisterBean(String username, String email, String passwrod) {
        this.username = username;
        this.email = email;
        this.password = passwrod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswrod(String passwrod) {
        this.password = passwrod;
    }
}
