package com.example;

import com.example.bean.ChangePasswordBean;
import com.example.bean.PasswordBean;
import com.example.bean.RegisterBean;
import com.example.bean.ScheduleInfoBean;
import com.example.model.*;
import com.example.service.ScheduleService;
import com.example.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/14.
 */
@RestController
@EnableJpaRepositories
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/")
    String hello() {
        return "user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, String> login(@RequestBody PasswordBean passwordBean) {
        System.out.println("===============");
        System.out.println("/login");
        String username = passwordBean.getUsername();
        String password = passwordBean.getPassword();
        System.out.println(username);
        System.out.println(password);

        Map<Integer, User> map = userService.logIn(username, password);

        Map<String, String> result = new HashMap<>();

//        Map<String, User>

        for (Integer key : map.keySet()) {
            if (key == 0) {
                result.put("error", "请验证邮箱后登录！");
            }
            if (key == 1) {
                JSONObject jsonStu = JSONObject.fromObject(map.get(key));
                System.out.println(jsonStu.toString());

                result.put("success", jsonStu.toString());
            }
            if (key == -1) {
                result.put("error", "您的账号已被封禁！");
            }
            if (key == 3) {
                result.put("error", "账号或密码错误！");
            }
        }

        return result;
//        return;
//        return userService.logIn("island", "123456");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Map<String, String> signIn(@RequestBody RegisterBean registerBean) {
//        return userService.logIn(",", ",");
        Map<String, String> result = new HashMap<>();
        System.out.println("===============");
        System.out.println("/register");
        String username = registerBean.getUsername();
        String password = registerBean.getPassword();
        String email = registerBean.getEmail();
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);

        if (userService.register(username, email, password)) {
            result.put("success", "注册成功");
        } else {
            result.put("error", "用户名已存在或邮箱已注册！");
        }
        return result;
    }

    @RequestMapping(value = "/authenticate/{username}", method = RequestMethod.GET)
    Map<String, String> authenticate(@PathVariable String username) {
        Map<String, String> result = new HashMap<>();

        if (userService.authenticate(username)) {
            result.put("success", "验证成功");
        } else {
            result.put("error", "验证失败");

        }
        return result;
    }

    @RequestMapping(value = "/info/{username}", method = RequestMethod.GET)
    User getUserInfo(@PathVariable String username) {
        return userService.getUserInfo(username);
    }

    /**
     * 获得用户对应折扣
     * @param grade
     * @return
     */
    @RequestMapping(value = "/vip/{grade}", method = RequestMethod.GET)
    int getVipDiscount(@PathVariable int grade) {
        return userService.getVipDiscount(grade);
    }

    /**
     * 线下购票
     * @param order
     * @return
     */
    @RequestMapping(value = "/buyTicket/offline", method = RequestMethod.POST)
    boolean buyTicketOffline(@RequestBody Order order) {
        System.out.println("===============");
        System.out.println("/buyTicket/offline");
        System.out.println(order.toString());
        return userService.buyTicketOffline(order);
    }

    /**
     * 获得所有用户
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 获得所有等级折扣
     * @return
     */
    @RequestMapping(value = "/discounts", method = RequestMethod.GET)
    List<Discount> getAllDiscounts() {
        return userService.getAllDiscounts();
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    Map<String, String> changePassword(@RequestBody ChangePasswordBean changePasswordBean) {
        System.out.println("===============");
        System.out.println("/password/change");
        System.out.println(changePasswordBean.toString());
        return userService.changePassword(changePasswordBean);
    }

    /**
     * 获得所有优惠券
     * @return
     */
    @RequestMapping(value = "/coupon/all", method = RequestMethod.GET)
    List<Coupon> getAllCoupons() {
        return userService.getAllCoupons();
    }

    /**
     * 获得用户所有优惠券
     * @return
     */
    @RequestMapping(value = "/coupon/my/{username}", method = RequestMethod.GET)
    List<Coupon> getMyCoupons(@PathVariable String username) {
        return userService.getMyCoupons(username);
    }

    /**
     * 修改密码
     * @param myCoupon
     * @return
     */
    @RequestMapping(value = "/coupon/exchange", method = RequestMethod.POST)
    boolean exchangeCoupon(@RequestBody MyCoupon myCoupon) {
        System.out.println("===============");
        System.out.println("/coupon/exchange");
        System.out.println(myCoupon.toString());
        return userService.exchangeCoupon(myCoupon);
    }

    /**
     * 获得最近三个演出
     * @return
     */
    @RequestMapping(value = "/schedule/close", method = RequestMethod.GET)
    List<Schedule> getThreeClosestSchedule() {
        return scheduleService.getThreeClosestSchedule();
    }

    /**
     * 获得最新三个演出
     * @return
     */
    @RequestMapping(value = "/schedule/new", method = RequestMethod.GET)
    List<Schedule> getThreeNewestSchedule() {
        return scheduleService.getThreeNewestSchedule();
    }

    /**
     * 获得计划相应信息
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/schedule/info/{schedule}", method = RequestMethod.GET)
    ScheduleInfoBean get(@PathVariable int schedule) {
        return scheduleService.getScheduleInfo(schedule);
    }

}
