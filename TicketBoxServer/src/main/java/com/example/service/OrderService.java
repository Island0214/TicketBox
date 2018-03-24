package com.example.service;

import com.example.bean.OrderCreateBean;
import com.example.bean.OrderPayBean;
import com.example.model.MyOrder;
import com.example.model.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by island on 2018/3/23.
 */
@Service
public interface OrderService {
    MyOrder createOrder(OrderCreateBean orderCreateBean);

    MyOrder getOrderById(MyOrder myOrder);

    Map<String, String> payOrder(OrderPayBean orderPayBean);
}
