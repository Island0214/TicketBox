package com.example;

import com.example.bean.DoubleInfoBean;
import com.example.bean.IntInfoBean;
import com.example.bean.PasswordBean;
import com.example.bean.VipStatisticsBean;
import com.example.model.*;
import com.example.service.BalanceService;
import com.example.service.ManagerService;
import com.example.service.ScheduleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@RestController
@EnableJpaRepositories
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String hello() {
        return "manager";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, String> login(@RequestBody PasswordBean passwordBean) {
        System.out.println("===============");
        System.out.println("/login");
        String username = passwordBean.getUsername();
        String password = passwordBean.getPassword();
        System.out.println(username);
        System.out.println(password);

        Map<String, String> result = new HashMap<>();

        if (managerService.login(username, password)) {
            result.put("success", "登录成功");
        } else {
            result.put("error", "账号或密码错误！");
        }

        return result;
    }

    /**
     * 根据场馆状态获得所有场馆
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/venues/{status}", method = RequestMethod.GET)
    List<Venue> getScheduleInfo(@PathVariable int status) {
        System.out.println("===============");
        System.out.println("/schedules");
        System.out.println(status);

        return managerService.getVenuesByStatus(status);
    }

    /**
     * 经理批准场馆注册/修改信息时修改场馆的状态
     * @param venue
     * @return
     */
    @RequestMapping(value = "/venue/status", method = RequestMethod.POST)
    boolean setVenueStatus(@RequestBody Venue venue) {
        System.out.println("===============");
        System.out.println("/venue/status");
        System.out.println(venue.toString());

        return managerService.approveVenue(venue);
    }

    /**
     * 经理获得所有已结束但未结算演出
     * @return
     */
    @RequestMapping(value = "/schedules/unpaid", method = RequestMethod.GET)
    List<Balance> getUnpaidSchedules() {
        System.out.println("===============");
        System.out.println("/schedules/unpaid");

        return balanceService.getUnpaidSchedules();
    }

    /**
     * 经理获得所有已结算演出
     * @return
     */
    @RequestMapping(value = "/schedules/paid", method = RequestMethod.GET)
    List<Balance> getPaidSchedules() {
        System.out.println("===============");
        System.out.println("/schedules/paid");

        return balanceService.getPaidSchedules();
    }

    /**
     *  经理结算演出
     * @param balance
     * @return
     */
    @RequestMapping(value = "/balance/pay", method = RequestMethod.POST)
    boolean paySchedule(@RequestBody Balance balance) {
        System.out.println("===============");
        System.out.println("/balance/pay");
        System.out.println(balance.toString());
        return balanceService.payBalance(balance);
    }


    /**
     * 经理结算场馆收入
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/schedule/balance", method = RequestMethod.POST)
    boolean setVenueStatus(@RequestBody Schedule schedule) {
        System.out.println("===============");
        System.out.println("/schedule/balance");
        System.out.println(schedule.toString());
        return false;

//        return managerService.approveVenue(venue);
    }

    @RequestMapping(value = "/user/ban", method = RequestMethod.POST)
    boolean banUser(@RequestBody User user) {
        System.out.println("===============");
        System.out.println("/user/ban");
        System.out.println(user.toString());
        return managerService.banUser(user);
    }


    /**
     * 获得场馆总演出数
     * @return
     */
    @RequestMapping(value = "/schedule/count/", method = RequestMethod.GET)
    List<IntInfoBean> getScheduleCountByVenue() {
        System.out.println("===============");
        System.out.println("/order/statistic/");
        return scheduleService.countByVenue();
    }

    /**
     * 获得场馆场均收入
     * @return
     */
    @RequestMapping(value = "/venue/income/", method = RequestMethod.GET)
    List<DoubleInfoBean> getVenueAverageIncome() {
        System.out.println("===============");
        System.out.println("/venue/income/");
        return balanceService.getVenueAverageIncome();
    }

    /**
     * 获得场馆场均收入
     * @return
     */
    @RequestMapping(value = "/venue/occupy/", method = RequestMethod.GET)
    List<DoubleInfoBean> getVenueAverageoccupy() {
        System.out.println("===============");
        System.out.println("/venue/occupy/");
        return managerService.getAverageOccupies();
    }

    /**
     * 获得场馆场均收入
     * @return
     */
    @RequestMapping(value = "/vip/statistics/", method = RequestMethod.GET)
    VipStatisticsBean getVipStatistics() {
        System.out.println("===============");
        System.out.println("/vip/statistics/");
        return userService.getVipStatistics();
    }
}
