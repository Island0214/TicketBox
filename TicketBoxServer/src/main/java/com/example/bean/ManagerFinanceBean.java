package com.example.bean;

import com.example.model.Balance;

import java.util.List;

/**
 * Created by island on 2018/3/26.
 */
public class ManagerFinanceBean {
    private double total;

    private List<Balance> balanceList;

    public ManagerFinanceBean() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Balance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<Balance> balanceList) {
        this.balanceList = balanceList;
    }

    @Override
    public String toString() {
        return "ManagerFinanceBean{" +
                "total=" + total +
                ", balanceList=" + balanceList +
                '}';
    }
}
