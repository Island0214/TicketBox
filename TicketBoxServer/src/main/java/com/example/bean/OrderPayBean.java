package com.example.bean;

/**
 * Created by island on 2018/3/23.
 */
public class OrderPayBean {
    private int orderId;

    public OrderPayBean() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderPayBean(int orderId) {
        this.orderId = orderId;
    }
}
