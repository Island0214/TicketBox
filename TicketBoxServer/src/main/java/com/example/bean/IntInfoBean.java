package com.example.bean;

/**
 * Created by island on 2018/3/26.
 */
public class IntInfoBean {
    private String name;

    private long value;

    public IntInfoBean() {
    }

    public IntInfoBean(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderTypeBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
