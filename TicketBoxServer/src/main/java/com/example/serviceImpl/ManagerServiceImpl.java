package com.example.serviceImpl;

import com.example.dao.ManagerRepository;
import com.example.dao.VenueRepository;
import com.example.model.Manager;
import com.example.model.Venue;
import com.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
