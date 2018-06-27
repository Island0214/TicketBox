package com.example.demo;

import com.example.bean.TourBean;
import com.example.service.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author keenan on 2018/6/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void testFindFiveTours() {
        List<TourBean> tourBeans = scheduleService.getFiveTours();
        System.out.println(tourBeans.toString());
    }
}
