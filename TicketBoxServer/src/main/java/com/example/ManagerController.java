package com.example;

import com.example.model.PasswordBean;
import com.example.model.User;
import com.example.model.Venue;
import com.example.service.ManagerService;
import net.sf.json.JSONObject;
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

    @RequestMapping(value = "/venue/status", method = RequestMethod.POST)
    boolean setVenueStatus(@RequestBody Venue venue) {
        System.out.println("===============");
        System.out.println("/venue/status");
        System.out.println(venue.toString());

        return managerService.approveVenue(venue);
    }


}
