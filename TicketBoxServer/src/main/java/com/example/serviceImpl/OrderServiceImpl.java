package com.example.serviceImpl;

import com.example.bean.OrderCreateBean;
import com.example.bean.OrderPayBean;
import com.example.dao.AliPayRepository;
import com.example.dao.BankAccountRepository;
import com.example.dao.OrderRepository;
import com.example.dao.SeatRepository;
import com.example.model.AliPay;
import com.example.model.BankAccount;
import com.example.model.MyOrder;
import com.example.model.Seat;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by island on 2018/3/23.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AliPayRepository aliPayRepository;

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
            seatString += orderCreateBean.getSeats().get(i) + ", ";
        }
        seatString = seatString.substring(0, seatString.length() - 2);
        MyOrder order = new MyOrder(type, orderCreateBean.getPrice(), orderCreateBean.getUsername(), orderCreateBean.getSchedule(), orderCreateBean.getArea(), seatString, new Date());
        System.out.println(order.toString());
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public MyOrder getOrderById(MyOrder myOrder) {
        return orderRepository.findById(myOrder.getOrder_id());
    }

    @Override
    public Map<String, String> payOrder(OrderPayBean orderPayBean) {
        MyOrder order = orderRepository.findById(orderPayBean.getOrder_id());
        Map<String, String> result = new HashMap<>();

        if (orderPayBean.getType().equals("网上银行")) {
            BankAccount bankAccount = bankAccountRepository.findByAccountAndPassword(orderPayBean.getCode(), orderPayBean.getPassword());
            if (bankAccount == null) {
                result.put("error", "支付账号或密码错误！");
            } else {
                if (bankAccount.getBalance() < order.getPrice()) {
                    result.put("error", "余额不足！");
                } else {
                    bankAccount.setBalance(bankAccount.getBalance() - order.getPrice());
                    order.setType("已付款订单");
                    orderRepository.save(order);
                    bankAccountRepository.save(bankAccount);
                    result.put("success", "支付成功！");
                }
            }
        } else {
            AliPay aliPay = aliPayRepository.findByAccountAndPassword(orderPayBean.getCode(), orderPayBean.getPassword());
            if (aliPay == null) {
                result.put("error", "支付账号或密码错误！");
            } else {
                if (aliPay.getBalance() < order.getPrice()) {
                    result.put("error", "余额不足！");
                } else {
                    aliPay.setBalance(aliPay.getBalance() - order.getPrice());
                    order.setType("已付款订单");
                    orderRepository.save(order);
                    aliPayRepository.save(aliPay);
                    result.put("success", "支付成功！");
                }
            }
        }
        return result;
    }
}
