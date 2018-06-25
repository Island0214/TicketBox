package com.example.model;

import javax.persistence.*;

/**
 * @author qianzhihao
 * @version 2018/6/25
 */
@Table(name = "advertisement")
@Entity
public class Advertisement {
    private int id;
    private String title;
    private int schedule_id;

    public Advertisement() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }
}
