package com.example.bean;

/**
 * Created by island on 2018/3/23.
 */
public class OrderPayBean {
    private int order_id;

    private String code;

    private String password;

    private String type;

    public OrderPayBean() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrderPayBean{" +
                "order_id=" + order_id +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
