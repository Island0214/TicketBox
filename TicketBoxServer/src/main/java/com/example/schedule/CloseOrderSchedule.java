package com.example.schedule;

import com.example.model.MyOrder;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/26.
 */
@Component
public class CloseOrderSchedule {
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedDelay = 1000)
    public void check() {
        List<MyOrder> orderList = orderService.findAllUnpaidOrders();
        for (int i = 0; i < orderList.size(); i++) {
//            System.out.println(orderList.get(i).toString());
//            System.out.println(new Date().getTime() - orderList.get(i).getTime().getTime());
//            System.out.println(1000 * 60 * 15);

            // 15分钟
            int time = 1000 * 60 * 15;
            if (new Date().getTime() - orderList.get(i).getTime().getTime() > time) {
                orderService.closeOrder(orderList.get(i).getOrder_id());
            }
        }
    }
}
