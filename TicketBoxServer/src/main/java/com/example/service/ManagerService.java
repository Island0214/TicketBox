package com.example.service;

import com.example.model.User;
import com.example.model.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by island on 2018/3/16.
 */
@Service
public interface ManagerService {
    boolean login(String username, String password);

    List<Venue> getVenuesByStatus(int status);

    boolean approveVenue(Venue venue);

    boolean banUser(User user);
}
