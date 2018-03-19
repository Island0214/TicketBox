package com.example.dao;

import com.example.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by island on 2018/3/19.
 */
public interface SeatRepository extends JpaSpecificationExecutor<Seat>, JpaRepository<Seat, Long> {
}
