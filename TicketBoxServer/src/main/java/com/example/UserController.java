package com.example;

import com.example.model.PasswordBean;
import com.example.model.RegisterBean;
import com.example.model.User;
import com.example.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            if (key == 2) {
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

    @RequestMapping(value = "/vip/{grade}", method = RequestMethod.GET)
    int getVipDiscount(@PathVariable int grade) {
        return userService.getVipDiscount(grade);

//        return userService.getUserInfo(username);
    }
}
