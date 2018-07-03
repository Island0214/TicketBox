package com.example.bean;

public class SeatSearchBean {
    private int scheduleId;
    private String area;
    private int row;
    private int col;

    public SeatSearchBean () {

    }

    public SeatSearchBean(int scheduleId, String area, int row, int col) {
        this.scheduleId = scheduleId;
        this.area = area;
        this.row = row;
        this.col = col;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "SeatSearchBean{" +
                "scheduleId=" + scheduleId +
                ", area='" + area + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
