package com.example.schedule;

import com.example.dao.OrderRepository;
import com.example.dao.ScheduleRepository;
import com.example.model.MyOrder;
import com.example.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/27.
 */
@Component
public class FinishOrderSchedule {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderFinish() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        for (int i = 0; i < scheduleList.size(); i++) {
            Schedule schedule = scheduleList.get(i);
            if (schedule.getTime().getTime() < new Date().getTime()) {
                List<MyOrder> orders = orderRepository.findByScheduleAndType(schedule.getSchedule_id(), "已付款订单");
                for (int j = 0; j < orders.size(); j++) {
                    MyOrder order = orders.get(j);
                    order.setType("已完成订单");
                    orderRepository.save(order);
                }

            }
//        List<MyOrder> orders = orderRepository.
        }
    }
}
