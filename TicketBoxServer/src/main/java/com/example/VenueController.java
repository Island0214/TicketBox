package com.example;

import com.example.model.*;
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

        for (int key : map.keySet()) {
            System.out.println(key);
            if (key == -1) {
                result.put("error", "登录失败");
            } else if (key == 0) {
                result.put("error", "请等待Tickets经理通过场馆申请！");
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

    /**
     * 获得场馆信息方法
     *
     * @param venue
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    Venue getInfo(@RequestBody Venue venue) {
        System.out.println("===============");
        System.out.println("/info");
        int code = venue.getCode();
        System.out.println(code);

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
        System.out.println("===============");
        System.out.println("/area");
        int code = venue.getCode();
        System.out.println(code);

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
        System.out.println("===============");
        System.out.println("/area/save");
        for (int i = 0; i < areas.length; i++) {
            Area area = areas[i];
            int venue = area.getVenue();
            String name = area.getName();
            int row = area.getRow();
            int col = area.getCol();
            System.out.println(venue);
            System.out.println(name);
            System.out.println(row);
            System.out.println(col);
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
        System.out.println("===============");
        System.out.println("/schedule/dispatch");
        System.out.println(schedule.toString());

        return scheduleService.saveSchedule(schedule);
//        return venueService.saveAreaInfo(areas);
    }

    /**
     * 保存计划座位信息方法
     *
     * @param seatListBean
     * @return
     */
    @RequestMapping(value = "/schedule/seats", method = RequestMethod.POST)
    boolean setScheduleSeat(@RequestBody SeatListBean seatListBean) {
        System.out.println("===============");
        System.out.println("/schedule/seats");
        System.out.println(seatListBean.toString());

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
        System.out.println("===============");
        System.out.println("/schedules");
//        System.out.println(seatListBean.toString());

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
        System.out.println("===============");
        System.out.println("/schedules");
        System.out.println(id);
//        System.out.println("/schedules");
//        System.out.println(seatListBean.toString());
        Map<String, String> result = new HashMap<>();
        List<Integer> prices = scheduleService.getAllPricesOfSchedule(id);
        result.put("prices", JSONArray.fromObject(prices).toString());
        System.out.println(prices.toString());
        for (int i = 0; i < prices.size(); i++) {
            System.out.println(prices.get(i));

            List<String> areas = scheduleService.getAreasByScheduleAndString(id, prices.get(i));
            System.out.println(areas.toString());
            result.put("price" + prices.get(i), JSONArray.fromObject(areas).toString());


            for (int j = 0; j < areas.size(); j++) {
                Seat seat = scheduleService.getSeatByScheduleAndArea(id, areas.get(j));
                System.out.println(seat.toString());
                result.put(areas.get(j), JSONObject.fromObject(seat).toString());
            }
        }
        return result;
    }

    @RequestMapping(value = "/seats/{id}/{area}", method = RequestMethod.GET)
    Map<String, String> getSeatsByArea(@PathVariable int id, @PathVariable String area) {
        System.out.println("===============");
        System.out.println("/seats");
        System.out.println(id);
        System.out.println(area);
        Map<String, String> result = new HashMap<>();
        Seat seat = scheduleService.getSeatByScheduleAndArea(id, area);
        result.put("seat", JSONObject.fromObject(seat).toString());

        List<Boolean> booleans = scheduleService.getSeatsInArea(id, area);
        result.put("seats", JSONArray.fromObject(booleans).toString());

        return result;
    }

}
