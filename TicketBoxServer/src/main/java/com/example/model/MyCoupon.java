package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/21.
 */
@Entity
public class MyCoupon {
    @Id
    private int coupon_id;

    private int coupon;

    private String username;

    private boolean used;

    public MyCoupon() {
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "MyCoupon{" +
                "coupon_id=" + coupon_id +
                ", coupon=" + coupon +
                ", username='" + username + '\'' +
                ", used=" + used +
                '}';
    }
}
