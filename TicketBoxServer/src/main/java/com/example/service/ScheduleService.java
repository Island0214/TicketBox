package com.example.service;

import com.example.model.Schedule;
import com.example.model.SeatListBean;
import org.springframework.stereotype.Service;

/**
 * Created by island on 2018/3/19.
 */
@Service
public interface ScheduleService {
    Schedule saveSchedule(Schedule schedule);

    boolean saveSeatInfo(SeatListBean seatListBean);
}
