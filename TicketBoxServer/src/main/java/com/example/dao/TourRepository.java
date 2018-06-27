package com.example.dao;

import com.example.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author keenan on 2018/6/27
 */
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findAll();
}
