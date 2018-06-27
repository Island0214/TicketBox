package com.example.bean;

import java.util.Date;

/**
 * @author keenan on 2018/6/27
 */
public class TourCityBean {
    private int scheduleId;

    private String city;

    private Date time;

    public TourCityBean(int scheduleId, String city, Date time) {
        this.scheduleId = scheduleId;
        this.city = city;
        this.time = time;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TourCityBean{" +
                "scheduleId=" + scheduleId +
                ", city='" + city + '\'' +
                ", time=" + time +
                '}';
    }
}
