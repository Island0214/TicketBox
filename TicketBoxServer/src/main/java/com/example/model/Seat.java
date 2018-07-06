package com.example.model;

import javax.persistence.*;

/**
 * Created by island on 2018/3/19.
 */
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seat_id;

    private int schedule;

    private String area;

    @Column(name = "seat_row")
    private int seat_row;

    private int col;

    private int price;

    /**
     * status == 0 未卖出
     * status == 1 等待付款
     * status == 2 已出售
     * status == 3 已检票
     */
    private int status;

    public Seat() {
    }

    public Seat(int schedule, String area, int seat_row, int col, int price, int status) {
        this.schedule = schedule;
        this.area = area;
        this.seat_row = seat_row;
        this.col = col;
        this.price = price;
        this.status = status;
    }

    public Seat(String area, int seat_row, int col) {
        this.area = area;
        this.seat_row = seat_row;
        this.col = col;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int row) {
        this.seat_row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return area + seat_row + "排" + col + "座";
    }

//    @Override
//    public String toString() {
//        return "Seat{" +
//                "seatId=" + seatId +
//                ", schedule=" + schedule +
//                ", area='" + area + '\'' +
//                ", row=" + row +
//                ", col=" + col +
//                ", price=" + price +
//                ", status=" + status +
//                '}';
//    }
}
