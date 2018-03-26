package com.example.serviceImpl;

import com.example.bean.ChangePasswordBean;
import com.example.bean.OrderTypeBean;
import com.example.bean.UsableCouponBean;
import com.example.dao.*;
import com.example.model.*;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.*;

/**
 * Created by island on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private MyCouponRepository myCouponRepository;

    @Override
    public Map<Integer, User> logIn(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        Map<Integer, User> map = new HashMap<>();

        if (user != null) {
            map.put(user.getStatus(), user);
        } else {
            map.put(3, null);
        }

        return map;
    }

    @Override
    public boolean register(String username, String email, String password) {
        List<User> userList = userRepository.findByUsernameOrEmail(username, email);
        if (userList.size() > 0) {
            return false;
        } else {
            User user = new User(username, email, password, 1);
            userRepository.save(user);

            // 收件人电子邮箱
            String to = email;

            // 发件人电子邮箱
            String from = "549378813@qq.com";

            // 指定发送邮件的主机为 smtp.qq.com
            String host = "smtp.qq.com";  //QQ 邮件服务器

            // 获取系统属性
            Properties properties = System.getProperties();

            // 设置邮件服务器
            properties.setProperty("mail.smtp.host", host);

            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            properties.put("mail.smtp.socketFactory.fallback", "false");
            properties.put("mail.smtp.starttls.enable", "true");

            properties.put("mail.smtp.auth", "true");
            // 获取默认session对象
            Session session = Session.getInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("549378813@qq.com", "pjskqhsgfjyzbeff"); //发件人邮件用户名、密码
                }
            });
            session.setDebug(true);


            try {
                // 创建默认的 MimeMessage 对象
                MimeMessage message = new MimeMessage(session);

                // Set From: 头部头字段
                message.setFrom(new InternetAddress(from));

                // Set To: 头部头字段
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));

                // Set Subject: 头部头字段
                message.setSubject("TicketBox 注册邮箱验证!");

                // 设置消息体
                message.setContent("<a href='http://localhost:8089/#/user/authenticate/" + username + "/'>click to authenticate!</a>", "text/html");

                // 发送消息
                Transport.send(message);
                System.out.println("Sent message successfully....from runoob.com");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
            return true;
        }
    }

    @Override
    public boolean authenticate(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if (user != null && user.getStatus() == 0) {
            user.setStatus(1);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserInfo(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setPassword("");
            return user;
        } else {
            return null;
        }
    }

    @Override
    public int getVipDiscount(int grade) {
        return discountRepository.findByGrade(grade).getDiscount();
    }

    @Override
    public boolean buyTicketOffline(Order order) {
        if (!order.getUsername().equals("")) {
            User user = userRepository.findByUsername(order.getUsername());
            user.setConsumption(user.getConsumption() + order.getPrice());
            int grade = discountRepository.findGradeByConsumption((int) user.getConsumption()).get(0).getGrade();
            user.setGrade(grade);
            user.setIntegration(user.getIntegration() + order.getPrice());
            userRepository.save(user);
        }
        String seatString = order.getSeat();

        for (int i = 0; i < seatString.split(", ").length; i++) {
            String seat = seatString.split(", ")[i];
            System.out.println(seat);
            int row = Integer.parseInt(seat.split("排")[0]);
            int col = Integer.parseInt(seat.split("排")[1].split("座")[0]);

            Seat seatObject = seatRepository.findByScheduleAndAreaAndRowAndCol(order.getSchedule(), order.getArea(), row, col);
            seatObject.setStatus(2);
            seatRepository.save(seatObject);
            System.out.println(seatObject.toString());

        }
        return true;
    }

    @Override
    public Order buyTicketOnline() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setPassword("");
        }
        return users;
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Map<String, String> changePassword(ChangePasswordBean changePasswordBean) {
        Map<String, String> result = new HashMap<>();
        User user = userRepository.findByUsernameAndPassword(changePasswordBean.getUsername(), changePasswordBean.getOldPassword());
        if (user == null) {
            result.put("error", "原密码错误！");
        } else {
            if (!changePasswordBean.getNewPassword1().equals(changePasswordBean.getNewPassword2())) {
                result.put("error", "两次密码输入不一致！！");
            } else {
                user.setPassword(changePasswordBean.getNewPassword1());
                userRepository.save(user);
                result.put("success", "成功修改密码！");
            }
        }
        return result;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> getMyCoupons(String username) {
        List<MyCoupon> myCoupons = myCouponRepository.findByUsernameAndUsed(username, false);
        List<Coupon> coupons = new ArrayList<>();
        for (int i = 0; i < myCoupons.size(); i++) {
            coupons.add(couponRepository.findById(myCoupons.get(i).getCoupon()));
        }
        return coupons;
    }

    @Override
    public boolean exchangeCoupon(MyCoupon myCoupon) {
        User user = userRepository.findByUsername(myCoupon.getUsername());
        Coupon coupon = couponRepository.findById(myCoupon.getCoupon());
        System.out.println(coupon);

        if (user != null && coupon != null && user.getIntegration() >= coupon.getIntegration()) {
            myCoupon.setUsed(false);
            myCouponRepository.save(myCoupon);
            user.setIntegration(user.getIntegration() - coupon.getIntegration());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Coupon> getUsableCoupons(UsableCouponBean usableCouponBean) {
        List<Integer> myCoupons = myCouponRepository.findDistinctByUsernameAndUsed(usableCouponBean.getUsername());
        List<Coupon> coupons = new ArrayList<>();

        for (int i = 0; i < myCoupons.size(); i++) {
            Coupon coupon = couponRepository.findById(myCoupons.get(i));
            if (coupon.getConsumption() <= usableCouponBean.getPrice()) {
                coupons.add(coupon);
            }
        }

        return coupons;
    }
}
