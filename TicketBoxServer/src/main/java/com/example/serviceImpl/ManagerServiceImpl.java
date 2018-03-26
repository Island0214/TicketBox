package com.example.serviceImpl;

import com.example.bean.DoubleInfoBean;
import com.example.dao.*;
import com.example.model.Manager;
import com.example.model.Schedule;
import com.example.model.User;
import com.example.model.Venue;
import com.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2018/3/16.
 */
@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public boolean login(String username, String password) {
        Manager manager = managerRepository.findByUsernameAndPassword(username, password);

        if (manager != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Venue> getVenuesByStatus(int status) {
        List<Venue> venues = venueRepository.findByStatus(status);
        for (int i = 0; i < venues.size(); i++) {
            venues.get(i).setPassword("");
        }
        return venues;
    }

    @Override
    public boolean approveVenue(Venue venue) {
        Venue targetVenue = venueRepository.findByCode(venue.getCode());
        if  (targetVenue == null) {
            return false;
        } else {
            targetVenue.setStatus(venue.getStatus());
            venueRepository.save(targetVenue);
            return true;
        }
    }

    @Override
    public boolean banUser(User user) {
        User targetUser = userRepository.findByUsername(user.getUsername());
        targetUser.setStatus(-1);
        userRepository.save(targetUser);
        return true;
    }

    @Override
    public List<DoubleInfoBean> getAverageOccupies() {
        List<Venue> venues = venueRepository.findAll();
        List<DoubleInfoBean> doubleInfoBeans = new ArrayList<>();

        for (int i = 0; i < venues.size(); i++) {
            List<Schedule> schedules = scheduleRepository.findByVenue(venues.get(i).getCode());
            double average = 0;

            for (int j = 0; j < schedules.size(); j++) {
                average += (double) seatRepository.getOccupaidSeats(schedules.get(j).getSchedule_id()) * 100
                        / (double) seatRepository.getScheduleSeats(schedules.get(j).getSchedule_id());
            }

            if (schedules.size() != 0) {
                doubleInfoBeans.add(new DoubleInfoBean(venues.get(i).getName(), average / schedules.size()));
            }
        }
        return doubleInfoBeans;
    }
}
