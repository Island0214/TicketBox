package com.example.bean;

import java.util.List;

/**
 * @author keenan on 2018/7/2
 */
public class OrderSearchResultBean {
    private List<OrderInfoBean> orders;
    private int pageNum;
    private int total;
    private int pageSize;

    public OrderSearchResultBean(List<OrderInfoBean> orders, int pageNum, int total, int pageSize) {
        this.orders = orders;
        this.pageNum = pageNum;
        this.total = total;
        this.pageSize = pageSize;
    }

    public OrderSearchResultBean() {
    }

    public List<OrderInfoBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfoBean> orders) {
        this.orders = orders;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
