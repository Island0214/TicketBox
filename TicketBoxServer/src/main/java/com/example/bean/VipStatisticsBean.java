package com.example.bean;

import java.util.List;

/**
 * Created by island on 2018/3/26.
 */
public class VipStatisticsBean {
    private int total_count;

    private List<IntInfoBean> intInfoBeans;

    public VipStatisticsBean() {
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<IntInfoBean> getIntInfoBeans() {
        return intInfoBeans;
    }

    public void setIntInfoBeans(List<IntInfoBean> intInfoBeans) {
        this.intInfoBeans = intInfoBeans;
    }

    @Override
    public String toString() {
        return "VipStatisticsBean{" +
                "total_count=" + total_count +
                ", intInfoBeans=" + intInfoBeans +
                '}';
    }
}
