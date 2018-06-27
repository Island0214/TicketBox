package com.example.demo;

import com.example.service.VenueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author keenan on 2018/6/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VenueServiceTest {
    @Autowired
    private VenueService venueService;


    @Test
    public void testRegister() {
        venueService.register("宝能国际体育演艺中心", "广州市开创大道2666号", "123456");
        venueService.register("福州海峡奥林匹克体育中心综合馆", "福州市南台岛仓山道山路59号", "123456");
        venueService.register("西安曲江国际会展中心B4馆", "西安市雁展路1号", "123456");
        venueService.register("辽宁体育馆", "沈阳市浑南五路附近，奥体中心西侧", "123456");
        venueService.register("南京奥体中心体育馆", "南京市河西新城区中心区域", "123456");
        venueService.register("梅赛德斯-奔驰文化中心", "上海市世博大道1200号", "123456");
        venueService.register("佛山市岭南明珠体育馆", "佛山市禅城区季华五路市广播电视塔对面广场", "123456");
        venueService.register("武汉客厅·中国文化博览中心A馆", "汉口金银潭宏图路8号 武汉客厅", "123456");
        venueService.register("双流体育中心", "成都市白河路延伸段体育中心", "123456");
    }


    @Test
    public void testAddArea() {

    }
}
