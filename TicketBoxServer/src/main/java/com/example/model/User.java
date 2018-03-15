package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/14.
 */
@Entity
public class User {
    @Id
    private int user_id;
    private String username;
    private String email;
    private int grade;
    private double consumption;
    private double integration;
    private int status;
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getIntegration() {
        return integration;
    }

    public void setIntegration(double integration) {
        this.integration = integration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int user_id, String username, String email, int grade, double consumption, double integration, int status, String password) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.grade = grade;
        this.consumption = consumption;
        this.integration = integration;
        this.status = status;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", grade=" + grade +
                ", consumption=" + consumption +
                ", integration=" + integration +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
