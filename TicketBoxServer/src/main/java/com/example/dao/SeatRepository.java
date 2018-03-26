package com.example.dao;

import com.example.bean.DoubleInfoBean;
import com.example.bean.IntInfoBean;
import com.example.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by island on 2018/3/19.
 */
public interface SeatRepository extends JpaSpecificationExecutor<Seat>, JpaRepository<Seat, Long> {
    @Query("select distinct s.price from Seat s where s.schedule = :schedule order by s.price")
    List<Integer> findPricesBySchedule(@Param("schedule") int schedule);

    @Query("select distinct s.area from Seat s where s.schedule = :schedule and s.price = :price")
    List<String> findAreasByScheduleAndPrice(@Param("schedule") int schedule, @Param("price") int price);

    @Query("select distinct s from Seat s where s.schedule = :schedule and s.area = :area order by s.row desc, s.col desc")
    List<Seat> findRowAndCodeByScheduleAndArea(@Param("schedule") int schedule, @Param("area") String area);

    @Query("select distinct s from Seat s where s.schedule = :schedule and s.area = :area order by s.row, s.col")
    List<Seat> findSeatsByScheduleAndArea(@Param("schedule") int schedule, @Param("area") String area);

    Seat findByScheduleAndAreaAndRowAndCol(int schedule, String area, int row, int col);

    List<Seat> findBySchedule(int schedule);

    @Query("select count(s) from Seat s where s.schedule = :schedule")
    int getScheduleSeats(@Param("schedule") int schedule);

    @Query("select count(s) from Seat s where s.schedule = :schedule and (s.status = 2 or s.status = 3)")
    int getOccupaidSeats(@Param("schedule") int schedule);


}
