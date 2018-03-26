package com.example.service;

import com.example.bean.DoubleInfoBean;
import com.example.bean.OrderCreateBean;
import com.example.bean.OrderPayBean;
import com.example.bean.OrderTypeBean;
import com.example.model.MyOrder;
import com.example.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/23.
 */
@Service
public interface OrderService {
    MyOrder createOrder(OrderCreateBean orderCreateBean);

    MyOrder getOrderById(MyOrder myOrder);

    Map<String, String> payOrder(OrderPayBean orderPayBean);

    Map<String, String> cancelOrder(OrderPayBean orderPayBean);

    Map<String, String> refundOrder(OrderPayBean orderPayBean);

    List<MyOrder> getAllOrders(String username);

    List<MyOrder> getOrdersByType(String username, String type);

    List<OrderTypeBean> getOrderStatistic(String username);

    List<DoubleInfoBean> getUserStatistic(String username);

    List<MyOrder> findAllUnpaidOrders();

    boolean closeOrder(int id);

}
