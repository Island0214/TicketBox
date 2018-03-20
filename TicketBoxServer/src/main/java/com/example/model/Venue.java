package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/16.
 */
@Entity
public class Venue {
    @Id
    private int code;

    private String name;

    private String password;

    /**
     * status == 0 注册未通过
     * status == 1 注册通过待添加座位信息
     * status == 2 注册通过且信息通过
     * status == -1 注册通过且信息修改后等待审核
     * status == -2 注册通过且信息未通过审核
     * status == -3 注册失败
     */
    private int status;

    private String address;

    public Venue() {
    }

    public Venue(int code, String name, int status, String address) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                '}';
    }
}
