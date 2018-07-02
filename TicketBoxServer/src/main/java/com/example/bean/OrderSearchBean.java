package com.example.bean;

/**
 * @author keenan on 2018/7/2
 */
public class OrderSearchBean {
    private String username;
    private String status;
    private int pageNum;
    private int pageSize;

    public OrderSearchBean() {
    }

    public OrderSearchBean(String username, String status, int pageNum, int pageSize) {
        this.username = username;
        this.status = status;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
