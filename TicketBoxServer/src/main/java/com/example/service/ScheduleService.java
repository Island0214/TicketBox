package com.example.service;

import com.example.bean.*;
import com.example.model.Schedule;
import com.example.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
@Service
public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    boolean saveSeatInfo(SeatListBean seatListBean);

    List<Schedule> getScheduleByVenue(int venue);

    List<Integer> getAllPricesOfSchedule(int schedule);

    List<String> getAreasByScheduleAndString(int schedule, int price);

    Seat getSeatByScheduleAndArea(int schedule, String area);

    Seat getSeatByScheduleAndAreaAndRowAndCol(int schedule, String area,int row,int col);

    List<Boolean> getSeatsInArea(int schedule, String area);

    List<Schedule> getThreeClosestSchedule();

    List<Schedule> getThreeNewestSchedule();

    ScheduleInfoBean getScheduleInfo(int scheduleId);

    Page<Schedule> findScheduleByPage(ScheduleSearchBean scheduleSearchBean);

    Schedule getScheduleById(int schedule);

    List<IntInfoBean> countByVenue();

    List<TourBean> getFiveTours();

    List<TourCityBean> getTourScheduleByTourId(int tourId);

    /**
     * 获得所有有巡演的城市
     * @return
     */
    List<String> getTourCities();

    /**
     * 获取一个城市的3个热门计划
     * @param city
     * @return
     */
    List<Schedule> get3SchedulesByCity(String city);

    /**
     * 获得热门戏剧
     *
     * @return
     */
    List<ScheduleBriefBean> getHotOperas();

    /**
     * 获得热门音乐会
     *
     * @return
     */
    List<ScheduleBriefBean> getHotConcerts();
}
