package com.example.serviceImpl;

import com.example.bean.*;
import com.example.dao.*;
import com.example.model.*;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public MyOrder createOrder(OrderCreateBean orderCreateBean) {
        List<String> seatIdList = new ArrayList<>(orderCreateBean.getSeats().size());
        List<String> seatOrderedList = new ArrayList<>(orderCreateBean.getSeats().size());
        boolean hasOrdered = false;
        for (Seat s : orderCreateBean.getSeats()) {
            Seat seat = seatRepository.findByScheduleAndAreaAndRowAndCol(orderCreateBean.getSchedule(), s.getArea(), s.getRow(), s.getCol());
            if (seat.getStatus() != 1) {
                seat.setStatus(1);
            } else {
                hasOrdered = true;
                seatOrderedList.add(String.valueOf(seat.getRow()) + "排" + String.valueOf(seat.getCol()) + "列");
            }
            s = seat;
            seatIdList.add(String.valueOf(seat.getSeat_id()));
        }
        if (hasOrdered) {
            return new MyOrder(null, String.join(",", seatOrderedList));
        } else {
            for (Seat s : orderCreateBean.getSeats()) {
                seatRepository.save(s);
            }
        }
        int venue = scheduleRepository.findById(orderCreateBean.getSchedule()).getVenue();
        MyOrder order = new MyOrder("待付款订单", orderCreateBean.getPrice(), orderCreateBean.getUsername(), orderCreateBean.getSchedule(), String.join(",", seatIdList), new Date(), venue);


//        String type = "待付款订单";
////        String seatString = orderCreateBean.getArea() + ": ";
//
//        for (int i = 0; i < orderCreateBean.getSeats().size(); i++) {
//            Seat seat = seatRepository.findByScheduleAndAreaAndRowAndCol(
//                    orderCreateBean.getSchedule(),
//                    Integer.parseInt(orderCreateBean.getSeats().get(i).split("排")[0]),
//                    Integer.parseInt(orderCreateBean.getSeats().get(i).split("排")[1].split("座")[0]));
//            System.out.println(seat.toString());
//            seat.setStatus(1);
//            seatRepository.save(seat);
//            seatString += orderCreateBean.getSeats().get(i) + ", ";
//        }
//
//        if (seatString.length() > 2) {
//            seatString = seatString.substring(0, seatString.length() - 2);
//        }
//        if (seatString.equals("")) {
//            seatString = "待分配";
//        }
//        MyOrder order = new MyOrder(type, orderCreateBean.getPrice(), orderCreateBean.getUsername(), orderCreateBean.getSchedule(), orderCreateBean.getArea(), seatString, new Date(), venue);
        System.out.println(order.toString());
        order = orderRepository.saveAndFlush(order);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 15000);// 设定指定的时间time,此处为2000毫秒

        return order;
    }

    @Override
    public MyOrder createPreorder(PreorderCreateBean preorderCreateBean) {
        List<Seat> emptySeats = seatRepository.findByScheduleAndPriceAndStatus(preorderCreateBean.schedule, preorderCreateBean.unitPrice, 0);
        if (emptySeats.size() < preorderCreateBean.num) {
            return new MyOrder(null, "该价格剩余座位不足");
        }

        List<String> seatIdList = new ArrayList<>(preorderCreateBean.num);
        for (int i = 0; i < preorderCreateBean.num; i++) {
            seatIdList.add(String.valueOf(emptySeats.get(i).getSeat_id()));
            emptySeats.get(i).setStatus(1);
            seatRepository.save(emptySeats.get(i));
        }

        int venue = scheduleRepository.findById(preorderCreateBean.schedule).getVenue();
        MyOrder order = new MyOrder("待付款订单", preorderCreateBean.unitPrice * preorderCreateBean.num, preorderCreateBean.username, preorderCreateBean.num, String.join(",", seatIdList), new Date(), venue);
        System.out.println(order.toString());
        order = orderRepository.saveAndFlush(order);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("-------设定要指定任务--------");
            }
        }, 15000);// 设定指定的时间time,此处为2000毫秒

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
            Schedule schedule = scheduleRepository.findById(order.getSchedule());
            long diff = schedule.getTime().getTime() - new Date().getTime();
            double rate = 1;
            if (diff < 24 * 60 * 60 * 1000) {
                rate = 0.6;
            } else if (diff < 5 * 24 * 60 * 60 * 1000) {
                rate = 0.7;
            } else if (diff < 9 * 24 * 60 * 60 * 1000) {
                rate = 0.8;
            } else if (diff < 15 * 24 * 60 * 60 * 1000) {
                rate = 0.9;
            }

            MyPay myPay = payRepository.findByOrderid(orderPayBean.getOrder_id());
            if (myPay != null) {
                if (myPay.getType().equals("网上银行")) {
                    BankAccount bankAccount = bankAccountRepository.findByAccount(myPay.getAccount());
                    bankAccount.setBalance(bankAccount.getBalance() + order.getPrice() * rate);
                    bankAccountRepository.save(bankAccount);
                } else {
                    AliPay aliPay = aliPayRepository.findByAccount(myPay.getAccount());
                    aliPay.setBalance(aliPay.getBalance() + order.getPrice() * rate);
                    aliPayRepository.save(aliPay);
                }
                order.setType("已取消订单");
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

    @Override
    public List<OrderTypeBean> getOrderStatistic(String username) {
        List<DoubleInfoBean> orderTypeBeans = orderRepository.findConsumptionStatistics(username);
        return orderRepository.findOrderStatistics(username);
    }

    @Override
    public List<DoubleInfoBean> getUserStatistic(String username) {
        return orderRepository.findConsumptionStatistics(username);
    }

    @Override
    public List<MyOrder> findAllUnpaidOrders() {
        return orderRepository.findAllUnpaidOrders();
    }

    @Override
    public boolean closeOrder(int id) {
        return false;
    }

    @Override
    public List<MyOrder> findByVenueAndType(int code) {
        return orderRepository.findByVenueAndType(code, "已退订订单");
    }

    /**
     * 根据用户名和订单状态搜索订单
     *
     * @param searchBean
     * @return
     */
    @Override
    public OrderSearchResultBean findOrdersByUsernameAndStatus(OrderSearchBean searchBean) {
        Pageable pageable = new PageRequest(searchBean.getPageNum() - 1, searchBean.getPageSize(), null);
        Page<MyOrder> myOrders = orderRepository.searchOrders(pageable, searchBean.getUsername(), searchBean.getStatus());
        List<OrderInfoBean> orders = new ArrayList<>();

        myOrders.getContent().forEach(myOrder -> {
            OrderInfoBean orderInfoBean = new OrderInfoBean();
            orderInfoBean.setOrderId(myOrder.getOrder_id());
            orderInfoBean.setType(myOrder.getType());
            orderInfoBean.setPrice(myOrder.getPrice());
            orderInfoBean.setUsername(myOrder.getUsername());
            orderInfoBean.setScheduleId(myOrder.getSchedule());
            orderInfoBean.setOrderTime(myOrder.getTime());

            Schedule schedule = scheduleRepository.findById(myOrder.getSchedule());
            orderInfoBean.setScheduleName(schedule.getSchedule());
            orderInfoBean.setScheduleTime(schedule.getTime());
            orderInfoBean.setPoster(schedule.getPoster());

            List<Integer> seatIds = Arrays.stream(myOrder.getSeatIds().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            List<String> seatsInfo = new ArrayList<>();
            seatIds.forEach(seatId -> {
                Seat seat = seatRepository.findOne(seatId);
                seatsInfo.add(seat.toString());
            });

            orderInfoBean.setSeatsInfo(seatsInfo);
            orders.add(orderInfoBean);
        });
        return new OrderSearchResultBean(orders, searchBean.getPageNum(), myOrders.getTotalPages(), searchBean.getPageSize());
    }
}
