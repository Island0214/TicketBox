package com.example.service;

import com.example.model.Area;
import com.example.model.Venue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by island on 2018/3/16.
 */
@Service
public interface VenueService {
    Map<Integer, Venue> login(int code, String password);

    Venue register(String name, String address, String password);

    Venue getVenueInfo(int code);

    List<Area> getAreaInfo(int code);

    boolean saveAreaInfo(Area[] areas);
}
