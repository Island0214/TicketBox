package com.example;

import com.example.model.PasswordBean;
import com.example.model.Venue;
import com.example.service.VenueService;
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

}
