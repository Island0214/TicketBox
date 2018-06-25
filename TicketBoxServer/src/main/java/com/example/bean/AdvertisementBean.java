package com.example.bean;

import com.example.model.Advertisement;

/**
 * @author qianzhihao
 * @version 2018/6/25
 */
public class AdvertisementBean {
    public String title;
    public int schedule_id;

    public AdvertisementBean(Advertisement ad) {
        this.title = ad.getTitle();
        this.schedule_id = ad.getSchedule_id();
    }
}
