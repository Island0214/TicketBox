package com.example.serviceImpl;

import com.example.bean.OrderCreateBean;
import com.example.dao.OrderRepository;
import com.example.dao.SeatRepository;
import com.example.model.MyOrder;
import com.example.model.Order;
import com.example.model.Seat;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by island on 2018/3/23.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public MyOrder createOrder(OrderCreateBean orderCreateBean) {
        String type = "待付款订单";
//        String seatString = orderCreateBean.getArea() + ": ";
        String seatString = "";

        for (int i = 0; i < orderCreateBean.getSeats().size(); i++) {
            Seat seat = seatRepository.findByScheduleAndAreaAndRowAndCol(
                    orderCreateBean.getSchedule(),
                    orderCreateBean.getArea(),
                    Integer.parseInt(orderCreateBean.getSeats().get(i).split("排")[0]),
                    Integer.parseInt(orderCreateBean.getSeats().get(i).split("排")[1].split("座")[0]));
            System.out.println(seat.toString());
            seat.setStatus(1);
            seatRepository.save(seat);
            seatString += orderCreateBean.getSeats().get(i)  + ", ";
        }
        seatString = seatString.substring(0, seatString.length() - 2);
        MyOrder order = new MyOrder(type, orderCreateBean.getPrice(), orderCreateBean.getUsername(), orderCreateBean.getSchedule(), orderCreateBean.getArea(), seatString, new Date());
        System.out.println(order.toString());
        order = orderRepository.saveAndFlush(order);
        return order;
    }
}
