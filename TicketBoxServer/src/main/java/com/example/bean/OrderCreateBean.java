package com.example.bean;

import java.util.List;

/**
 * Created by island on 2018/3/22.
 */
public class OrderCreateBean {

    private String username;

    private double price;

    private int coupon;

    private int schedule;

    private String area;

    private List<String> seats;

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

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "OrderCreateBean{" +
                "username='" + username + '\'' +
                ", price=" + price +
                ", coupon=" + coupon +
                ", schedule=" + schedule +
                ", area='" + area + '\'' +
                ", seats=" + seats +
                '}';
    }
}
