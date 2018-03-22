package com.example.service;

import com.example.bean.ChangePasswordBean;
import com.example.bean.UsableCouponBean;
import com.example.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/14.
 */
@Service
public interface UserService {
    Map<Integer, User> logIn(String username, String password);

    boolean register(String username, String email, String password);

    boolean authenticate(String username);

    User getUserInfo(String username);

    int getVipDiscount(int grade);

    boolean buyTicketOffline(Order order);

    Order buyTicketOnline();

    List<User> getAllUsers();

    List<Discount> getAllDiscounts();

    Map<String, String> changePassword(ChangePasswordBean changePasswordBean);

    List<Coupon> getAllCoupons();

    List<Coupon> getMyCoupons(String username);

    boolean exchangeCoupon(MyCoupon myCoupon);

    List<Coupon> getUsableCoupons(UsableCouponBean usableCouponBean);
}
