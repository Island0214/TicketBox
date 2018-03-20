package com.example.serviceImpl;

import com.example.dao.AreaRepository;
import com.example.dao.SeatRepository;
import com.example.dao.VenueRepository;
import com.example.model.Area;
import com.example.model.Seat;
import com.example.model.Venue;
import com.example.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Seat seat1 = seatRepository.findByScheduleAndAreaAndRowAndCol(seat.getSchedule(), seat.getArea(), seat.getRow(), seat.getCol());
        System.out.println(seat1.toString());
        Map<String, String> map = new HashMap<>();
        String seatString = seat.getRow() + "排" + seat.getCol() + "座";
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
}
