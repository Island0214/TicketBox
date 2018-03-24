package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/24.
 */
@Entity
public class MyPay {
    @Id
    private int id;

    private int orderid;

    private String account;

    private String type;

    public MyPay() {
    }

    public MyPay(int orderid, String account, String type) {
        this.orderid = orderid;
        this.account = account;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
