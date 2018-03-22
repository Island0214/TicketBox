package com.example.bean;

/**
 * Created by island on 2018/3/22.
 */
public class UsableCouponBean {
    private String username;

    private int price;

    public UsableCouponBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UsableCouponBean{" +
                "username='" + username + '\'' +
                ", price=" + price +
                '}';
    }
}
