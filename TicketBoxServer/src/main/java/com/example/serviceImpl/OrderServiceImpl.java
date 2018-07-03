package com.example.serviceImpl;

import com.example.bean.*;
import com.example.dao.*;
import com.example.model.*;
import com.example.service.OrderService;
import org.hibernate.validator.constraints.URL;
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

    @Autowired
    private UserRepository userRepository;

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
        List<String> seatIdList = new ArrayList<>();
        List<Seat> orderedSeats = new ArrayList<>();
        int sumPrice = 0;
        for (PreOrderPriceNumBean pn : preorderCreateBean.priceNums) {
            sumPrice += pn.price * pn.num;
            List<Seat> emptySeats = seatRepository.findByScheduleAndPriceAndStatus(preorderCreateBean.schedule, pn.price, 0);
            if (emptySeats.size() < pn.num) {
                return new MyOrder(null, String.valueOf(pn.price) + "元的剩余座位不足");
            }
            for (int i = 0; i < pn.num; i++) {
                seatIdList.add(String.valueOf(emptySeats.get(i).getSeat_id()));
                emptySeats.get(i).setStatus(1);
                orderedSeats.add(emptySeats.get(i));
            }
        }

        orderedSeats.forEach(seat -> seatRepository.save(seat));


        int venue = scheduleRepository.findById(preorderCreateBean.schedule).getVenue();
        MyOrder order = new MyOrder("待付款订单", sumPrice, preorderCreateBean.username, preorderCreateBean.schedule, String.join(",", seatIdList), new Date(), venue);
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

    /**
     * 将订单设为已付款订单状态，，变更会员等级和总消费
     */
    @Override
    public Map<String, String> payOrder(OrderPayBean orderPayBean) {
        MyOrder order = orderRepository.findById(orderPayBean.getOrderId());
        Map<String, String> result = new HashMap<>();
        result.put("success", "支付成功!");

        order.setType("已付款订单");
        orderRepository.save(order);

        User user = userRepository.findByUsername(order.getUsername());
        user.setConsumption(user.getConsumption() + order.getPrice());
        user.setIntegration(user.getIntegration() + order.getPrice() / 10);

        user.setGrade(getUserGrade(user.getConsumption()));
        userRepository.save(user);
        return result;
    }

    @Override
    /**
     * 将订单设为已取消订单状态，同时将相关座位状态设为0
     */
    public Map<String, String> cancelOrder(OrderPayBean orderPayBean) {
        Map<String, String> result = new HashMap<>();
        MyOrder order = orderRepository.findById(orderPayBean.getOrderId());
        if (order != null && order.getType().equals("待付款订单")) {
            order.setType("已取消订单");
            orderRepository.save(order);

            List<Integer> seatIds = Arrays.stream(order.getSeatIds().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            seatIds.forEach(seatId -> {
                Seat seat = seatRepository.findOne(seatId);
                seat.setStatus(0);
                seatRepository.save(seat);
            });
            result.put("success", "取消成功");
        } else {
            result.put("error", "取消失败");
        }
        return result;
    }

    @Override
    /**
     * 订单设为已取消订单状态，同时将相关座位状态设为0，变更会员等级和总消费
     */
    public Map<String, String> refundOrder(OrderPayBean orderPayBean) {
        Map<String, String> result = new HashMap<>();
        MyOrder order = orderRepository.findById(orderPayBean.getOrderId());
        if (order != null && order.getType().equals("已付款订单")) {
            order.setType("已取消订单");
            orderRepository.save(order);

            List<Integer> seatIds = Arrays.stream(order.getSeatIds().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            seatIds.forEach(seatId -> {
                Seat seat = seatRepository.findOne(seatId);
                seat.setStatus(0);
                seatRepository.save(seat);
            });

            User user = userRepository.findByUsername(order.getUsername());
            user.setConsumption(user.getConsumption() - order.getPrice());
            user.setIntegration(user.getIntegration() - order.getPrice() / 10);
            user.setGrade(getUserGrade(user.getIntegration()));
            userRepository.save(user);
            result.put("success", "退款成功！");
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

    private int getUserGrade(double integration) {
        if (integration >= 8000) {
            return 6;
        } else if (integration >= 4000) {
            return 5;
        } else if (integration >= 2000) {
            return 4;
        } else if (integration >= 1000) {
            return 3;
        } else if (integration >= 500) {
            return 2;
        } else return 1;
    }
}
