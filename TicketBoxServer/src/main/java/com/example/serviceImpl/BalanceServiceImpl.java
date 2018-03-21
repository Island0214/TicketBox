package com.example.serviceImpl;

import com.example.dao.BalanceRepository;
import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.Balance;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.service.BalanceService;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/20.
 */
@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public List<Balance> getUnpaidSchedules() {

        Calendar cal = Calendar.getInstance();
        Date date = new Date(cal.get(Calendar.YEAR) - 1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        System.out.println(date);
        List<Schedule> schedules = scheduleRepository.findFinishedByStatus(0, date);
//        List<Schedule> schedules = scheduleRepository.findFinishedByStatus(0, tomorrow.getTime());

        List<Balance> balances = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            double income = 0;
            List<Seat> seatList = seatRepository.findBySchedule(schedule.getSchedule_id());
            for (int j = 0; j < seatList.size(); j++) {
                if (seatList.get(j).getStatus() == 2 || seatList.get(j).getStatus() == 3) {
                    income += seatList.get(j).getPrice();
                }
            }
//            seatRepository.
            Balance balance = new Balance(schedule.getVenue(), schedule.getSchedule_id(), schedule.getSchedule(), schedule.getTime(), income);

            balances.add(balance);
//            System.out.println(schedules.get(i).getTime());
        }
        return balances;
    }

    @Override
    public boolean payBalance(Balance balance) {
        balance.setIncome(balance.getIncome() * 0.8);
        Balance result = balanceRepository.save(balance);
        Schedule schedule = scheduleRepository.findById(balance.getSchedule());
        System.out.println(schedule.toString());
        schedule.setStatus(1);
        scheduleRepository.save(schedule);
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Balance> getPaidSchedules() {
//        List<Balance> = get
        return balanceRepository.findAll();
    }
}
