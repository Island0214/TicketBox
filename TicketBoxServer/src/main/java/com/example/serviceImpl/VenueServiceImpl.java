package com.example.serviceImpl;

import com.example.bean.AdvertisementBean;
import com.example.bean.DoubleInfoBean;
import com.example.bean.IntInfoBean;
import com.example.dao.*;
import com.example.model.*;
import com.example.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by island on 2018/3/16.
 */
@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public Map<Integer, Venue> login(int code, String password) {
        Venue venue = venueRepository.findByCodeAndPassword(code, password);
        Map<Integer, Venue> result = new HashMap<>();

        if (venue == null) {
            result.put(-3, null);
        } else {
            result.put(venue.getStatus(), venue);
        }
        return result;
    }

    @Override
    public Venue register(String name, String address, String password) {
        Venue venue = venueRepository.findByName(name);
        if (venue != null) {
            return null;
        } else {
            venue = new Venue();
            venue.setStatus(0);
            venue.setAddress(address);
            venue.setName(name);
            venue.setPassword(password);
            venueRepository.save(venue);
            return venueRepository.findByName(name);
        }
    }

    @Override
    public Venue getVenueInfo(int code) {
        Venue venue = venueRepository.findByCode(code);
        return new Venue(venue.getCode(), venue.getName(), venue.getStatus(), venue.getAddress());
    }

    @Override
    public boolean saveAreaInfo(Area[] areas) {
        if (areas.length == 0) {
            return false;
        } else {
            List<Area> areaList = areaRepository.findByVenue(areas[0].getVenue());

            areaRepository.delete(areaList);

            for (int i = 0; i < areas.length; i++) {
                System.out.println(areas[i].toString());
            }

            areaRepository.save(Arrays.asList(areas));

            Venue venue = venueRepository.findByCode(areas[0].getVenue());
            venue.setStatus(-1);
            venueRepository.save(venue);

            return true;
        }
    }

    @Override
    public List<Area> getAreaInfo(int code) {
        return areaRepository.findByVenue(code);
    }

    @Override
    public Map<String, String> checkTicket(Seat seat) {
        Seat seat1 = seatRepository.findByScheduleAndAreaAndSeat_rowAndCol(seat.getSchedule(), seat.getArea(), seat.getSeat_row(), seat.getCol());
        System.out.println(seat1.toString());
        Map<String, String> map = new HashMap<>();
        String seatString = seat.getSeat_row() + "排" + seat.getCol() + "座";
        if (seat1.getStatus() == 2) {
            seat1.setStatus(3);
            seatRepository.save(seat1);
            map.put("success", seatString + "检票成功！");
        } else if (seat1.getStatus() == 3) {
            map.put("error", seatString + "已检过票！");
        } else {
            map.put("error", seatString + "尚未卖出！");
        }
        return map;
    }

    @Override
    public List<DoubleInfoBean> getVenueOrderStatistic(int code) {
        List<Schedule> scheduleList = scheduleRepository.findByVenue(code);

        List<DoubleInfoBean> list = new ArrayList<>();

        for (int i = 0; i < scheduleList.size(); i++) {
            double all = seatRepository.getScheduleSeats(scheduleList.get(i).getSchedule_id());
            double occupied = seatRepository.getOccupaidSeats(scheduleList.get(i).getSchedule_id());

            System.out.println(all);
            System.out.println(occupied);

            DecimalFormat df = new DecimalFormat("#.00");

            list.add(new DoubleInfoBean(scheduleList.get(i).getSchedule(), Double.parseDouble(df.format(occupied * 100 / all))));
        }
        return list;
    }

    @Override
    public List<IntInfoBean> getDayOrderStatistic(int code) {
        return orderRepository.findDailyByVenue(code);
    }

    @Override
    public List<Balance> getBalanceByVenue(int code) {
        return balanceRepository.findByVenue(code);
    }

    @Override
    public List<AdvertisementBean> getAdvertisements() {
        List<Advertisement> ads = advertisementRepository.findAll();
        List<AdvertisementBean> res = new ArrayList<>();
        int startIndex = new Random().nextInt(ads.size());
        for(int i=0;i<5;i++){
            res.add(new AdvertisementBean(ads.get((startIndex + i)%ads.size())));
        }
        return res;
    }

    @Override
    public List<Schedule> hotSchedules() {
        List<Schedule> res = new ArrayList<>();
        int min = scheduleRepository.minOfScheduleId();
        int max = scheduleRepository.maxOfScheduleId();


        while (res.size()<12){
            int rand_index = min + new Random().nextInt(max-min);

            Schedule schedule = scheduleRepository.findById(rand_index);
            if(schedule!=null&&!isIn(res,schedule)){
                res.add(schedule);
            }
        }
        return res;
    }

    @Override
    public List<Schedule> comingSchedules() {
        return scheduleRepository.findTop6ByTimeAfterOrderByTime(new Date());
    }

    private boolean isIn(List<Schedule> scheduleList,Schedule schedule){
        if(schedule.getTourId()==0){
            for(Schedule s:scheduleList){
                if(s.getSchedule_id()==schedule.getSchedule_id()){
                    return true;
                }
            }
            return false;
        }else {
            for(Schedule s:scheduleList){
                if(s.getTourId()==s.getTourId()){
                    return true;
                }
            }
            return false;
        }
    }
}
