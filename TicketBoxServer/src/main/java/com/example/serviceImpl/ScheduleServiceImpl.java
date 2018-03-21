package com.example.serviceImpl;

import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.Balance;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.model.SeatListBean;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
                    seatList.add(new Seat(seatListBean.getSchedule(), area.getArea(), j + 1, k + 1, area.getPrice(), 0));
                }
            }
        }

        seatRepository.save(seatList);
        return true;
    }

    @Override
    public List<Schedule> getScheduleByVenue(int venue) {
//        scheduleRepository.findByVenueBefore(int venue)
        return scheduleRepository.findByVenueBefore(venue, new Date());
    }

    @Override
    public List<Integer> getAllPricesOfSchedule(int schedule) {
        return seatRepository.findPricesBySchedule(schedule);
    }

    @Override
    public List<String> getAreasByScheduleAndString(int schedule, int price) {
        return seatRepository.findAreasByScheduleAndPrice(schedule, price);
    }

    @Override
    public Seat getSeatByScheduleAndArea(int schedule, String area) {
        List<Seat> seatList = seatRepository.findRowAndCodeByScheduleAndArea(schedule, area);
        if (seatList.size() > 0) {
            return seatList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Boolean> getSeatsInArea(int schedule, String area) {
        List<Seat> seatList = seatRepository.findSeatsByScheduleAndArea(schedule, area);
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < seatList.size(); i++) {
            if (seatList.get(i).getStatus() == 0) {
                result.add(false);
            } else {
                result.add(true);
            }
        }
        return result;
    }

}
