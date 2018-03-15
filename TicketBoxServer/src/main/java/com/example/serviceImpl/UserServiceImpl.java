package com.example.serviceImpl;

import com.example.dao.UserRepository;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by island on 2018/3/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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
            Session session = Session.getInstance(properties,new Authenticator(){
                public PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("549378813@qq.com", "pjskqhsgfjyzbeff"); //发件人邮件用户名、密码
                }
            });
            session.setDebug(true);


            try{
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
                message.setContent("<a href='http://localhost:8080/user/authenticate/"+ username + "/'>click to authenticate!</a>", "text/html" );

                // 发送消息
                Transport.send(message);
                System.out.println("Sent message successfully....from runoob.com");
            }catch (MessagingException mex) {
                mex.printStackTrace();
            }
            return true;
        }
    }

    @Override
    public boolean authenticate(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getStatus() == 0) {
            user.setStatus(1);
            userRepository.save(user);
        }
        return false;
    }
}
