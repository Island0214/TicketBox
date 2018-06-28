package com.example.bean;

import java.util.Date;

/**
 * Created by island on 2018/3/22.
 */
public class ScheduleSearchBean {
    private String city;
    private String category;
    private Date startTime;
    private Date endTime;
    private String userInput;
    private int pageNum;
    private int pageSize;

    public ScheduleSearchBean(String city, String category, Date startTime, Date endTime, String userInput, int pageNum, int pageSize) {
        this.city = city;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userInput = userInput;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public ScheduleSearchBean() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
