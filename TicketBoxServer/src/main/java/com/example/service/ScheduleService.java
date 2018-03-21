package com.example.service;

import com.example.model.Balance;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.model.SeatListBean;
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

    List<Boolean> getSeatsInArea(int schedule, String area);
}
