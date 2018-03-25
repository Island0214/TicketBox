package com.example.serviceImpl;

import com.example.bean.OrderCreateBean;
import com.example.bean.OrderPayBean;
import com.example.dao.*;
import com.example.model.*;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private MyCouponRepository myCouponRepository;

    @Override
    public MyOrder createOrder(OrderCreateBean orderCreateBean) {
        String type = "待付款订单";
//        String seatString = orderCreateBean.getArea() + ": ";
        String seatString = "";
//        orderCreateBean.getCoupon();
//        myCouponRepository.
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

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 15000);// 设定指定的时间time,此处为2000毫秒

        if (orderCreateBean.getCoupon() != 0) {
            MyCoupon myCoupon = myCouponRepository.findByCouponAndUsernameAndUsed(orderCreateBean.getCoupon(), orderCreateBean.getUsername(), false).get(0);
            myCoupon.setUsed(true);
            myCouponRepository.save(myCoupon);
        }

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
//                    Pay pay = new Pay();
                    MyPay myPay = new MyPay(order.getOrder_id(), orderPayBean.getCode(), orderPayBean.getType());
                    payRepository.save(myPay);
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
                    MyPay myPay = new MyPay(order.getOrder_id(), orderPayBean.getCode(), orderPayBean.getType());
                    payRepository.save(myPay);
                    result.put("success", "支付成功！");
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, String> cancelOrder(OrderPayBean orderPayBean) {
        Map<String, String> result = new HashMap<>();
        MyOrder order = orderRepository.findById(orderPayBean.getOrder_id());
        if (order != null && order.getType().equals("待付款订单")) {
            order.setType("已取消订单");
            orderRepository.save(order);
            result.put("success", "取消成功");
        } else {
            result.put("error", "取消失败");
        }
        return result;
    }

    @Override
    public Map<String, String> refundOrder(OrderPayBean orderPayBean) {
        Map<String, String> result = new HashMap<>();
        MyOrder order = orderRepository.findById(orderPayBean.getOrder_id());
        if (order != null && order.getType().equals("已付款订单")) {
            MyPay myPay = payRepository.findByOrderid(orderPayBean.getOrder_id());
            if (myPay != null) {
                if (myPay.getType().equals("网上银行")) {
                    BankAccount bankAccount = bankAccountRepository.findByAccount(myPay.getAccount());
                    bankAccount.setBalance(bankAccount.getBalance() + order.getPrice());
                    bankAccountRepository.save(bankAccount);
                } else {
                    AliPay aliPay = aliPayRepository.findByAccount(myPay.getAccount());
                    aliPay.setBalance(aliPay.getBalance() + order.getPrice());
                    aliPayRepository.save(aliPay);
                }
                order.setType("已退订订单");
                orderRepository.save(order);
                result.put("success", "退款成功！");
            } else {
                result.put("error", "退款失败！");
            }
        } else {
            result.put("error", "订单状态错误！");
        }
        return result;
    }

    @Override
    public List<MyOrder> getAllOrders(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    public List<MyOrder> getOrdersByType(String username, String type) {
        return orderRepository.findByUsernameAndType(username, type);
    }
}
