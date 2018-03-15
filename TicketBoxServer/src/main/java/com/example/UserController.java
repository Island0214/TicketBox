package com.example;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
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
        return "asd";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, String> login (@RequestParam String username,
                  @RequestParam String password) {
        System.out.println("===============");
        System.out.println("/login");
        System.out.println(username);
        System.out.println(password);

        Map<Integer, User> map = userService.logIn(username, password);

        Map<String, String> result = new HashMap<>();

//        Map<String, User>

        for (Integer key: map.keySet()) {
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

    @RequestMapping("/register")
    boolean signIn() {
//        return userService.logIn(",", ",");
        return false;
    }
}
