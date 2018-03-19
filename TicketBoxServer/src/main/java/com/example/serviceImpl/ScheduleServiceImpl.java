package com.example.serviceImpl;

import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.model.SeatListBean;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public boolean saveSeatInfo(SeatListBean seatListBean) {
        List<Seat> seatList = new ArrayList<>();

        List<Seat> areas = seatListBean.getSeatList();

        for (int i = 0; i < areas.size(); i++) {
            Seat area = areas.get(i);
            System.out.println(area.getRow());
            System.out.println(area.getCol());
            for (int j = 0; j < area.getRow(); j++) {
                for (int k = 0; k < area.getCol(); k++) {
                    seatList.add(new Seat(seatListBean.getSchedule(), area.getArea(), j + 1, k + 1, area.getPrice(), false));
                }
            }
        }

        seatRepository.save(seatList);
        return true;
    }
}
