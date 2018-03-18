package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/18.
 */
@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int area_id;

    private int venue;

    private String name;

    private int row;

    private int col;

    public Area() {
    }

    public Area(int venue, String name, int row, int col) {
        this.venue = venue;
        this.name = name;
        this.row = row;
        this.col = col;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getVenue() {
        return venue;
    }

    public void setVenue(int venue) {
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
