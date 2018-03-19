package com.example.model;

import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
public class SeatListBean {
    private int schedule;

    private List<Seat> seatList;

    public SeatListBean() {
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    @Override
    public String toString() {
        return "SeatListBean{" +
                "schedule=" + schedule +
                ", seatList=" + seatList +
                '}';
    }
}
