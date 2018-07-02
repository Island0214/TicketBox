package com.example.schedule;

import com.example.dao.OrderRepository;
import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.MyOrder;
import com.example.model.Schedule;
import com.example.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/26.
 */
@Component
public class SeatDispatchSchedule {
    //    0 0 0 1/1 * ? *
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SeatRepository seatRepository;


//    @Scheduled(cron = "0 0 0 * * ?")
//    public void dispatchSeat() {
//        List<Schedule> scheduleList = scheduleRepository.findAll();
//        System.out.println("===========");
//
//        for (int i = 0; i < scheduleList.size(); i++) {
//            Schedule schedule = scheduleList.get(i);
//            if (schedule.getTime().getTime() - new Date().getTime() < 15 * 24 * 60 * 1000) {
//                System.out.println(schedule.getSchedule_id());
//                List<MyOrder> orders = orderRepository.findByScheduleAndSeat(schedule.getSchedule_id(), "待分配");
//
//                for (int j = 0; j < orders.size(); j++) {
//                    MyOrder order = orders.get(j);
//                    System.out.println(order.toString());
//                    int tickets = Integer.parseInt(order.getArea().split(",")[0]);
//                    int price = Integer.parseInt(order.getArea().split(",")[1]);
//                    System.out.println(tickets);
//                    System.out.println(price);
//                    //获得该价位所有区域
//                    List<String> areas = seatRepository.findAreasByScheduleAndPrice(order.getSchedule(), price);
//                    System.out.println(areas.size());
//                    int areaCount = 0;
//                    List<Seat> seatList = seatRepository.findByScheduleAndStatusAndArea(order.getSchedule(), 0, areas.get(areaCount));
//
//                    while (seatList.size() < tickets) {
//                        areaCount++;
//                        seatList = seatRepository.findByScheduleAndStatusAndArea(order.getSchedule(), 0, areas.get(areaCount));
//                    }
//                    String seatString = "";
//
//
//                    for (int k = 0; k < tickets; k++) {
//                        Seat seat = seatList.get(i);
//                        System.out.println(seat.toString());
////                        seat.setStatus(2);
//                        seatRepository.save(seat);
//                        seatString += seat.getRow() + "排" + seat.getCol() + "座, ";
//                    }
//                    seatString = seatString.substring(0, seatString.length() - 2);
//                    order.setSeat(seatString);
//                    order.setArea(areas.get(areaCount));
//                    orderRepository.save(order);
//
//                }
//            }
//            System.out.println("===========");
//
//        }
//    }
}
