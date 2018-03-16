package com.example;

import com.example.model.PasswordBean;
import com.example.model.Venue;
import com.example.model.VenueRegisterBean;
import com.example.service.VenueService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@RestController
@EnableJpaRepositories
@RequestMapping("/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @RequestMapping("/")
    String hello() {
        return "venue";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, String> login(@RequestBody PasswordBean passwordBean) {
        System.out.println("===============");
        System.out.println("/login");
        String username = passwordBean.getUsername();
        String password = passwordBean.getPassword();
        System.out.println(username);
        System.out.println(password);
//
        Map<String, String> result = new HashMap<>();
//
        Map<Integer, Venue> map = venueService.login(Integer.parseInt(username), password);

        for (int key: map.keySet()) {
            System.out.println(key);
            if (key == -1) {
                result.put("error", "登录失败");
            }
            else if (key == 0) {
                result.put("error", "请等待Tickets经理通过场馆申请！");
            }
            else {
                result.put("success", "登录成功！");
            }
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Map<String, String> resgiter(@RequestBody VenueRegisterBean venueRegisterBean) {
        System.out.println("===============");
        System.out.println("/login");
        String name = venueRegisterBean.getName();
        String password = venueRegisterBean.getPassword();
        String address = venueRegisterBean.getAddress();
        System.out.println(name);
        System.out.println(password);
        System.out.println(address);
//
        Map<String, String> result = new HashMap<>();

        if (name.equals("") || password.equals("") || address.equals("")) {
            result.put("error", "信息不能为空！");
            return result;
        }

        Venue venue = venueService.register(name, address, password);
        result.put("venue", JSONObject.fromObject(venue).toString());
        return result;
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    Venue getInfo(@RequestBody Venue venue) {
        System.out.println("===============");
        System.out.println("/info");
        int code = venue.getCode();
        System.out.println(code);

        Venue venueInfo = venueService.getVenueInfo(code);
//        venueInfo.setPassword("");
//        Venue result = new
//        result.put("venue", JSONObject.fromObject(venue).toString());
        return venueInfo;
    }

}
