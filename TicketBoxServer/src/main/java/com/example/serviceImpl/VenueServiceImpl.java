package com.example.serviceImpl;

import com.example.dao.VenueRepository;
import com.example.model.Venue;
import com.example.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueRepository venueRepository;

    @Override
    public Map<Integer, Venue> login(int code, String password) {
        Venue venue = venueRepository.findByCodeAndPassword(code, password);
        Map<Integer, Venue> result = new HashMap<>();

        if (venue == null) {
            result.put(-1, null);
        } else {
            result.put(venue.getStatus(), venue);
        }
        return result;
    }
}
