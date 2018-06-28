package com.example.dao;

import com.example.bean.IntInfoBean;
import com.example.bean.ScheduleBriefBean;
import com.example.bean.TourCityBean;
import com.example.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by island on 2018/3/18.
 */
public interface ScheduleRepository extends JpaSpecificationExecutor<Schedule>, JpaRepository<Schedule, Integer> {
    @Query("select s from Schedule s where s.venue = :venue and s.time > :time")
    List<Schedule> findByVenueBefore(@Param("venue") int venue, @Param("time") Date time);

    List<Schedule> findByVenue(int venue);

    @Query("select s from Schedule s where s.status = :status and s.time <= :time")
    List<Schedule> findFinishedByStatus(@Param("status") int status, @Param("time") Date time);

    @Query("select s from Schedule s where s.schedule_id = :id")
    Schedule findById(@Param("id") int id);

    @Query(value = "select s from Schedule s where s.time > :date order by s.time")
    List<Schedule> findClosetThree(@Param("date") Date date);

    @Query(value = "select s from Schedule s order by s.schedule_id desc")
    List<Schedule> findNewestThree();

//    @Query("select s from Schedule s where s.type like %:stype% and s.time >= :start and s.time <= :end and (s.schedule like %:name% or s.artist like %:name% )")
//    Page<Schedule> findByParams(Pageable pageable, @Param("name") String name, @Param("stype") String stype, @Param("start") Date start, @Param("end") Date end);

    @Query("select new com.example.bean.IntInfoBean(concat(s.venue, ''), count (s)) from Schedule s group by concat(s.venue, '')")
    List<IntInfoBean> countByVenue();

    @Query("select max(s.schedule_id) from Schedule s")
    int maxOfScheduleId();

    @Query("select min(s.schedule_id) from Schedule s")
    int minOfScheduleId();

    List<Schedule> findTop6ByTimeAfterOrderByTime(Date date);

    @Query("select new com.example.bean.TourCityBean(s.schedule_id, s.city, s.time) from Schedule s where s.tourId= :tourId order by s.time ASC")
    List<TourCityBean> findSchedulesByTourId(@Param("tourId") int tourId);

    @Query("select new com.example.bean.TourCityBean(s.schedule_id, s.city, s.time) from Schedule s")
    List<TourCityBean> findTourCity();

    @Query("select new com.example.bean.ScheduleBriefBean(s.schedule_id,s.schedule) from Schedule s where s.type=:type and s.time>:date order by s.time")
    List<ScheduleBriefBean> findSchedulesByType(@Param("type") String type, @Param("date") Date date);

    List<Schedule> findTop3ByCityAndTimeAfter(String city, Date time);

    @Query("select s from Schedule s where s.type like %:category% and s.time>= :startTime and s.time<= :endTime and s.city like %:city% and (s.schedule like %:userInput% or s.artist like %:userInput%)")
    Page<Schedule> searchSchedules(Pageable pageable, @Param("city") String city, @Param("category") String category, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("userInput") String userInput);
}
