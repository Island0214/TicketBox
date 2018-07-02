package com.example.bean;

import com.example.model.Seat;

import java.util.List;

/**
 * Created by island on 2018/3/22.
 */
public class OrderCreateBean {

    private String username;

    private double price;

    private int schedule;

    private List<Seat> seats;

    public OrderCreateBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "OrderCreateBean{" +
                "username='" + username + '\'' +
                ", price=" + price +
                ", schedule=" + schedule +
                ", seats=" + seats +
                '}';
    }
}
