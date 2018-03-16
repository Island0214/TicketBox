package com.example;

import com.example.model.PasswordBean;
import com.example.model.User;
import com.example.service.ManagerService;
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

}
