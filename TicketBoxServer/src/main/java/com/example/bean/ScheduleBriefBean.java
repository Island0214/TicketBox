package com.example.bean;

/**
 * @author keenan on 2018/6/28
 */
public class ScheduleBriefBean {
    private int sheduleId;

    private String scheduleName;

    public ScheduleBriefBean(int sheduleId, String scheduleName) {
        this.sheduleId = sheduleId;
        this.scheduleName = scheduleName;
    }

    public ScheduleBriefBean() {
    }

    public int getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(int sheduleId) {
        this.sheduleId = sheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }
}
