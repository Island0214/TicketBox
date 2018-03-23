package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by island on 2018/3/23.
 */
@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private String type;

    private double price;

    private String username;

    private int schedule;

    private String area;

    private String seat;

    private Date time;

    public MyOrder() {
    }

    public MyOrder(String type, double price, String username, int schedule, String area, String seat, Date time) {
        this.type = type;
        this.price = price;
        this.username = username;
        this.schedule = schedule;
        this.area = area;
        this.seat = seat;
        this.time = time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", schedule=" + schedule +
                ", area='" + area + '\'' +
                ", seat='" + seat + '\'' +
                ", time=" + time +
                '}';
    }
}
