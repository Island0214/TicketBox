package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by island on 2018/3/20.
 */
@Entity
public class Balance {
    @Id
    private int balance_id;

    private int venue;

    private int schedule;

    private String name;

    private Date time;

    private double income;

    public Balance() {
    }

    public Balance(int venue, int schedule, String name, Date time, double income) {
        this.venue = venue;
        this.schedule = schedule;
        this.name = name;
        this.time = time;
        this.income = income;
    }

    public int getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(int balance_id) {
        this.balance_id = balance_id;
    }

    public int getVenue() {
        return venue;
    }

    public void setVenue(int venue) {
        this.venue = venue;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balance_id='" + balance_id + '\'' +
                ", venue=" + venue +
                ", schedule=" + schedule +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", income=" + income +
                '}';
    }
}
