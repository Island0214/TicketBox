package com.example.service;

import com.example.bean.AdvertisementBean;
import com.example.bean.DoubleInfoBean;
import com.example.bean.IntInfoBean;
import com.example.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@Service
public interface VenueService {
    Map<Integer, Venue> login(int code, String password);

    Venue register(String name, String address, String password);

    Venue getVenueInfo(int code);

    List<Area> getAreaInfo(int code);

    boolean saveAreaInfo(Area[] areas);

    Map<String, String> checkTicket(Seat seat);

    List<DoubleInfoBean> getVenueOrderStatistic(int code);

    List<IntInfoBean> getDayOrderStatistic(int code);

    List<Balance> getBalanceByVenue(int code);

    List<AdvertisementBean> getAdvertisements();

    /**
     * 6个热门演出(目前使用随机算法)
     * @return
     */
    List<Schedule> hotSchedules();
}
