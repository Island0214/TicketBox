package com.example.serviceImpl;

import com.example.bean.*;
import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.dao.TourRepository;
import com.example.dao.VenueRepository;
import com.example.model.Schedule;
import com.example.model.Seat;
import com.example.model.Tour;
import com.example.model.Venue;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by island on 2018/3/19.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public boolean saveSeatInfo(SeatListBean seatListBean) {
        List<Seat> seatList = new ArrayList<>();

        List<Seat> areas = seatListBean.getSeatList();

        for (int i = 0; i < areas.size(); i++) {
            Seat area = areas.get(i);
            System.out.println(area.getRow());
            System.out.println(area.getCol());
            for (int j = 0; j < area.getRow(); j++) {
                for (int k = 0; k < area.getCol(); k++) {
                    seatList.add(new Seat(seatListBean.getSchedule(), area.getArea(), j + 1, k + 1, area.getPrice(), 0));
                }
            }
        }

        seatRepository.save(seatList);
        return true;
    }

    @Override
    public List<Schedule> getScheduleByVenue(int venue) {
//        scheduleRepository.findByVenueBefore(int venue)
        return scheduleRepository.findByVenueBefore(venue, new Date());
    }

    @Override
    public List<Integer> getAllPricesOfSchedule(int schedule) {
        return seatRepository.findPricesBySchedule(schedule);
    }

    @Override
    public List<String> getAreasByScheduleAndString(int schedule, int price) {
        return seatRepository.findAreasByScheduleAndPrice(schedule, price);
    }

    @Override
    public Seat getSeatByScheduleAndArea(int schedule, String area) {
        List<Seat> seatList = seatRepository.findRowAndCodeByScheduleAndArea(schedule, area);
        if (seatList.size() > 0) {
            return seatList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Seat getSeatByScheduleAndAreaAndRowAndCol(int schedule, String area, int row, int col) {
        return seatRepository.findByScheduleAndAreaAndRowAndCol(schedule,area,row,col);
    }

    @Override
    public List<Boolean> getSeatsInArea(int schedule, String area) {
        List<Seat> seatList = seatRepository.findSeatsByScheduleAndArea(schedule, area);
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < seatList.size(); i++) {
            if (seatList.get(i).getStatus() == 0) {
                result.add(false);
            } else {
                result.add(true);
            }
        }
        return result;
    }

    @Override
    public List<Schedule> getThreeClosestSchedule() {
        Date date = new Date();
        List<Schedule> allSchedules = scheduleRepository.findClosetThree(date);
        List<Schedule> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            result.add(allSchedules.get(i));
        }
        return result;
    }

    @Override
    public List<Schedule> getThreeNewestSchedule() {
        List<Schedule> allSchedules = scheduleRepository.findNewestThree();
        List<Schedule> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(allSchedules.get(i));
        }
        return result;
    }

    @Override
    public ScheduleInfoBean getScheduleInfo(int scheduleId) {
        System.out.println(scheduleId);
        ScheduleInfoBean scheduleInfoBean = new ScheduleInfoBean();
        scheduleInfoBean.setScheduleId(scheduleId);

        Schedule schedule = scheduleRepository.findById(scheduleId);
        System.out.println(schedule);
        Venue venue = venueRepository.findByCode(schedule.getVenue());
        scheduleInfoBean.setVenueName(venue.getName());

        List<Integer> prices = seatRepository.findPricesBySchedule(scheduleId);
        System.out.println(prices.size());
        scheduleInfoBean.setMaxPrice(Collections.max(prices));
        scheduleInfoBean.setMinPrice(Collections.min(prices));

        return scheduleInfoBean;
    }

    @Override
    public Page<Schedule> findScheduleByPage(ScheduleSearchBean search) {
        String sortWay = search.getSort();
        Sort sort = null;
        if (sortWay == null || sortWay.equals("") || sortWay.equals("hottest")) {
            sort = new Sort(Sort.Direction.DESC, "hotValue");
        } else if (sortWay.equals("newest")) {
            sort = new Sort(Sort.Direction.DESC, "schedule_id");
        } else {
            sort = new Sort(Sort.Direction.ASC, "time");
        }
        Pageable pageable = new PageRequest(search.getPageNum() - 1, search.getPageSize(), sort);
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        int year = calendar.get(Calendar.YEAR);

        Date startTime = search.getStartTime();
        Date endTime = search.getEndTime();
        String city = search.getCity();
        String category = search.getCategory();
        String userInput = search.getUserInput();


        if (city == null || city.equals("")) {
            city = "";
        }
        if (category == null || category.equals("")) {
            category = "";
        }
        if (userInput == null || userInput.equals("")) {
            userInput = "";
        }

        if (startTime == null && endTime == null) {
            startTime = today;
            calendar.set(Calendar.YEAR, year + 5);
            endTime = calendar.getTime();
        } else if (startTime != null && endTime != null) {
            if (startTime.before(today)) {
                startTime = today;
            }
            if (endTime.before(today)) {
                endTime = today;
            }
        }

        return scheduleRepository.searchSchedules(pageable, city, category, startTime, endTime, userInput);
    }

    @Override
    public Schedule getScheduleById(int schedule) {
        return scheduleRepository.findById(schedule);
    }

    @Override
    public List<IntInfoBean> countByVenue() {
        List<IntInfoBean> intInfoBeans = scheduleRepository.countByVenue();

        for (int i = 0; i < intInfoBeans.size(); i++) {
            intInfoBeans.get(i).setName(
                    venueRepository.findByCode(Integer.parseInt(intInfoBeans.get(i).getName())).getName()
            );
        }
        return intInfoBeans;
    }

    @Override
    public List<TourBean> getFiveTours() {
        List<Tour> tours = tourRepository.findAll();
        List<TourBean> tourBeans = new ArrayList<>();
        Set<Integer> indexSet = new HashSet<>();

        Random random = new Random();
        for (int i = 0, j; i < 5; i++) {
            j = random.nextInt(tours.size());
            if (!indexSet.contains(j)) {
                indexSet.add(j);
                Tour tour = tours.get(j);
                List<TourCityBean> tourCityBeans = scheduleRepository.findSchedulesByTourId(tour.getTour_id());
                TourBean tourBean = new TourBean(tour.getTour_id(), tour.getArtist(), tour.getTourName(), tour.getPoster(), tourCityBeans);
                tourBeans.add(tourBean);
            } else {
                i--;
            }
        }

        return tourBeans;
    }


    @Override
    public List<TourCityBean> getTourScheduleByTourId(int tourId) {
        return scheduleRepository.findSchedulesByTourId(tourId);
    }

    /**
     * 获得所有有巡演的城市
     *
     * @return
     */
    @Override
    public List<String> getTourCities() {
        List<TourCityBean> tourCityBeans = scheduleRepository.findTourCity();
        Set<String> cities = new HashSet<>();
        tourCityBeans.forEach(tourCityBean -> cities.add(tourCityBean.getCity()));
        return new ArrayList<>(cities);
    }


    @Override
    public List<Schedule> get3SchedulesByCity(String city) {
        return scheduleRepository.findTop3ByCityAndTimeAfter(city, new Date());
    }

    /**
     * 获得热门戏剧
     *
     * @return
     */
    @Override
    public List<ScheduleBriefBean> getHotOperas() {
        List<ScheduleBriefBean> scheduleBriefBeans = scheduleRepository.findSchedulesByType("戏剧", new Date());
        return scheduleBriefBeans.subList(0, 5);
    }

    /**
     * 获得热门音乐会
     *
     * @return
     */
    @Override
    public List<ScheduleBriefBean> getHotConcerts() {
        List<ScheduleBriefBean> scheduleBriefBeans = scheduleRepository.findSchedulesByType("音乐会", new Date());
        return scheduleBriefBeans.subList(0, 5);
    }
}
