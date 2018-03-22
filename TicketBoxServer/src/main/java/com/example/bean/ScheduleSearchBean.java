package com.example.bean;

import java.util.Date;

/**
 * Created by island on 2018/3/22.
 */
public class ScheduleSearchBean {
    private String name;

    private String type;

    private int page;

    private Date start;

    private Date end;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ScheduleSearchBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ScheduleSearchBean{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
