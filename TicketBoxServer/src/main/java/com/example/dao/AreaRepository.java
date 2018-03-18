package com.example.dao;

import com.example.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by island on 2018/3/18.
 */
@Repository
public interface AreaRepository extends JpaSpecificationExecutor<Area>, JpaRepository<Area, Long> {
    List<Area> findByVenue(int venue);
}
