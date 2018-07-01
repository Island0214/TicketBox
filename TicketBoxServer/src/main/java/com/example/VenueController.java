package com.example;

import com.example.bean.*;
import com.example.model.*;
import com.example.service.OrderService;
import com.example.service.ScheduleService;
import com.example.service.VenueService;
import net.sf.json.JSONArray;
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
@RequestMapping("/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    String hello() {
        return "venue";
    }

    /**
     * 场馆登录方法
     *
     * @param passwordBean
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, String> login(@RequestBody PasswordBean passwordBean) {
        String username = passwordBean.getUsername();
        String password = passwordBean.getPassword();
        Map<String, String> result = new HashMap<>();
        Map<Integer, Venue> map = venueService.login(Integer.parseInt(username), password);

        for (int key : map.keySet()) {
            if (key == -3) {
                result.put("error", "场馆编号或密码错误");
            } else if (key == 0) {
                result.put("error", "请等待Tickets经理通过场馆注册申请！");
            } else {
                result.put("success", "登录成功！");
            }
        }
        return result;
    }

    /**
     * 场馆注册方法
     *
     * @param venueRegisterBean
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    Map<String, String> resgiter(@RequestBody VenueRegisterBean venueRegisterBean) {
        String name = venueRegisterBean.getName();
        String password = venueRegisterBean.getPassword();
        String address = venueRegisterBean.getAddress();
        Map<String, String> result = new HashMap<>();

        if (name.equals("") || password.equals("") || address.equals("")) {
            result.put("error", "信息不能为空！");
            return result;
        }

        Venue venue = venueService.register(name, address, password);
        result.put("venue", JSONObject.fromObject(venue).toString());
        return result;
    }

    /**
     * 获得场馆信息方法
     *
     * @param venue
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    Venue getInfo(@RequestBody Venue venue) {
        int code = venue.getCode();

        Venue venueInfo = venueService.getVenueInfo(code);
        return venueInfo;
    }

    /**
     * 获得场馆区域信息方法
     *
     * @param venue
     * @return
     */
    @RequestMapping(value = "/area", method = RequestMethod.POST)
    List<Area> getAreaInfo(@RequestBody Venue venue) {
        int code = venue.getCode();

        List<Area> areaList = venueService.getAreaInfo(code);

//        Venue venueInfo = venueService.getVenueInfo(code);
        return areaList;
    }

    /**
     * 修改场馆区域信息方法
     *
     * @param areas
     * @return
     */
    @RequestMapping(value = "/area/save", method = RequestMethod.POST)
    boolean saveArea(@RequestBody Area[] areas) {
        for (int i = 0; i < areas.length; i++) {
            Area area = areas[i];
            int venue = area.getVenue();
            String name = area.getName();
            int row = area.getRow();
            int col = area.getCol();
        }

        return venueService.saveAreaInfo(areas);
    }

    /**
     * 场馆发布计划方法
     *
     * @param schedule
     * @return
     */
    @RequestMapping(value = "/schedule/dispatch", method = RequestMethod.POST)
    Schedule saveSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule);
    }

    /**
     * 保存计划座位信息方法
     *
     * @param seatListBean
     * @return
     */
    @RequestMapping(value = "/schedule/seats", method = RequestMethod.POST)
    boolean setScheduleSeat(@RequestBody SeatListBean seatListBean) {
        return scheduleService.saveSeatInfo(seatListBean);
    }

    /**
     * 获得场馆所有未开场计划
     *
     * @param venue
     * @return
     */
    @RequestMapping(value = "/schedules/{venue}", method = RequestMethod.GET)
    List<Schedule> getVenueSchedules(@PathVariable int venue) {
        return scheduleService.getScheduleByVenue(venue);
    }

    /**
     * 获得计划售票详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    Map<String, String> getScheduleInfo(@PathVariable int id) {
        Map<String, String> result = new HashMap<>();
        List<Integer> prices = scheduleService.getAllPricesOfSchedule(id);
        result.put("prices", JSONArray.fromObject(prices).toString());
        for (int i = 0; i < prices.size(); i++) {
            List<String> areas = scheduleService.getAreasByScheduleAndString(id, prices.get(i));
            result.put("price" + prices.get(i), JSONArray.fromObject(areas).toString());

            for (int j = 0; j < areas.size(); j++) {
                Seat seat = scheduleService.getSeatByScheduleAndArea(id, areas.get(j));
                result.put(areas.get(j), JSONObject.fromObject(seat).toString());
            }
        }
        return result;
    }

    @RequestMapping(value = "/seats/{id}/{area}", method = RequestMethod.GET)
    Map<String, String> getSeatsByArea(@PathVariable int id, @PathVariable String area) {
        Map<String, String> result = new HashMap<>();
        Seat seat = scheduleService.getSeatByScheduleAndArea(id, area);
        result.put("seat", JSONObject.fromObject(seat).toString());

        List<Boolean> booleans = scheduleService.getSeatsInArea(id, area);
        result.put("seats", JSONArray.fromObject(booleans).toString());

        return result;
    }


    /**
     * 场馆现场检票
     *
     * @param seat
     * @return
     */
    @RequestMapping(value = "/seat/check", method = RequestMethod.POST)
    Map<String, String> setScheduleSeat(@RequestBody Seat seat) {
        return venueService.checkTicket(seat);
    }

    /**
     * 获得上座率统计
     *
     * @return
     */
    @RequestMapping(value = "/order/statistic/{code}", method = RequestMethod.GET)
    List<DoubleInfoBean> getVenueOrderStatistic(@PathVariable int code) {
        return venueService.getVenueOrderStatistic(code);
    }

    /**
     * 获得场馆所有退订统计
     *
     * @return
     */
    @RequestMapping(value = "/order/refund/{code}", method = RequestMethod.GET)
    List<MyOrder> getVenueRefundOrder(@PathVariable int code) {
        return orderService.findByVenueAndType(code);
    }

    /**
     * 获得预定统计
     *
     * @return
     */
    @RequestMapping(value = "/day/statistic/{code}", method = RequestMethod.GET)
    List<IntInfoBean> getDayOrderStatistic(@PathVariable int code) {
        return venueService.getDayOrderStatistic(code);
    }

    /**
     * 获得财务统计
     *
     * @return
     */
    @RequestMapping(value = "/finance/statistic/{code}", method = RequestMethod.GET)
    List<Balance> getFinanceStatistic(@PathVariable int code) {
        return venueService.getBalanceByVenue(code);
    }

    /**
     * 获得广告信息
     *
     * @return
     */
    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    List<AdvertisementBean> getAdvertisements() {
        return venueService.getAdvertisements();
    }

    /**
     * 获得热门计划
     *
     * @return
     */
    @RequestMapping(value = "/hotSchedules", method = RequestMethod.GET)
    List<Schedule> getHotSchedules() {
        return venueService.hotSchedules();
    }

    /**
     * 获得即将开始的6个
     *
     * @return
     */
    @RequestMapping(value = "/comingSchedules", method = RequestMethod.GET)
    List<Schedule> getComingSchedules() {
        return venueService.comingSchedules();
    }

    /**
     * 获得所有城市
     *
     * @return
     */
    @RequestMapping(value = "/tourCities", method = RequestMethod.GET)
    List<String> getTourCities() {
        return scheduleService.getTourCities();
    }

    /**
     * 随机获得五个巡演
     *
     * @return
     */
    @RequestMapping(value = "/randomTours", method = RequestMethod.GET)
    List<TourBean> getRandomFiveTours() {
        return scheduleService.getFiveTours();
    }

    @RequestMapping(value = "/tourSchedule/{tourId}",method = RequestMethod.GET)
    List<TourCityBean> getTourScheduleById(@PathVariable int tourId){
        return scheduleService.getTourScheduleByTourId(tourId);
    }

    /**
     * 获得5个热门戏剧，一段时间内要保持相同
     *
     * @return
     */
    @RequestMapping(value = "/hotOperas", method = RequestMethod.GET)
    List<ScheduleBriefBean> getHotOperas() {
        return scheduleService.getHotOperas();
    }

    /**
     * 获得5个热门音乐会，一段时间内要保持相同
     *
     * @return
     */
    @RequestMapping(value = "/hotConcerts", method = RequestMethod.GET)
    List<ScheduleBriefBean> getHotConcerts() {
        return scheduleService.getHotConcerts();
    }

    /**
     * 根据城市获取3个演出
     *
     * @return
     */
    @RequestMapping(value = "/citySchedules", method = RequestMethod.GET)
    List<Schedule> getCitySchedule(String city) {
        return scheduleService.get3SchedulesByCity(city);
    }
}
