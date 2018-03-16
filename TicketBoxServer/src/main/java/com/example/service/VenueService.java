package com.example.service;

import com.example.model.Venue;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@Service
public interface VenueService {
    Map<Integer, Venue> login(int code, String password);
}
