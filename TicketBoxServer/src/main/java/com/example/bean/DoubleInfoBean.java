package com.example.bean;

/**
 * Created by island on 2018/3/26.
 */

public class DoubleInfoBean {
    private String name;

    private double value;

    public DoubleInfoBean() {
    }

    public DoubleInfoBean(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleInfoBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
