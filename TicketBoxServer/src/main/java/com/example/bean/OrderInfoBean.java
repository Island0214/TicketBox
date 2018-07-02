package com.example.bean;

import java.util.Date;
import java.util.List;

/**
 * @author keenan on 2018/7/2
 */
public class OrderInfoBean {
    private int orderId;

    private String type;

    private double price;

    private String username;

    private int scheduleId;

    private String scheduleName;

    private Date scheduleTime;

    private Date orderTime;

    private List<String> seatsInfo;

    private String poster;

    public OrderInfoBean(int orderId, String type, double price, String username, int scheduleId, String scheduleName, Date scheduleTime, Date orderTime, List<String> seatsInfo, String poster) {
        this.orderId = orderId;
        this.type = type;
        this.price = price;
        this.username = username;
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleTime = scheduleTime;
        this.orderTime = orderTime;
        this.seatsInfo = seatsInfo;
        this.poster = poster;
    }

    public OrderInfoBean() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public List<String> getSeatsInfo() {
        return seatsInfo;
    }

    public void setSeatsInfo(List<String> seatsInfo) {
        this.seatsInfo = seatsInfo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
