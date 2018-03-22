package com.example.bean;

/**
 * Created by island on 2018/3/22.
 */
public class ScheduleInfoBean {
    private int scheduleId;

    private String venueName;

    private int minPrice;

    private int maxPrice;

    public ScheduleInfoBean() {
    }

    public ScheduleInfoBean(int scheduleId, String venueName, int minPrice, int maxPrice) {
        this.scheduleId = scheduleId;
        this.venueName = venueName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "ScheduleInfoBean{" +
                "scheduleId=" + scheduleId +
                ", venueName='" + venueName + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
