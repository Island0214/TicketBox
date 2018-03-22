package com.example.dao;

import com.example.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/18.
 */
public interface ScheduleRepository extends JpaSpecificationExecutor<Schedule>, JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s where s.venue = :venue and s.time > :time")
    List<Schedule> findByVenueBefore(@Param("venue") int venue, @Param("time") Date time);

    @Query("select s from Schedule s where s.status = :status and s.time <= :time")
    List<Schedule> findFinishedByStatus(@Param("status") int status, @Param("time") Date time);

    @Query("select s from Schedule s where s.schedule_id = :id")
    Schedule findById(@Param("id") int id);

    @Query(value = "select s from Schedule s where s.time > :date order by s.time")
    List<Schedule> findClosetThree(@Param("date") Date date);

    @Query(value = "select s from Schedule s order by s.schedule_id desc")
    List<Schedule> findNewestThree();
}
