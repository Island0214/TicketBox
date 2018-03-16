package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/16.
 */
@Entity
public class Venue {
    @Id
    private int venue_id;

    private int code;

    private String name;

    private String password;

    private int status;

    public Venue() {
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
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
}
