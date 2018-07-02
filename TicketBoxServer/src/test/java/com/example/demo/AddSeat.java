package com.example.demo;

import com.example.dao.ScheduleRepository;
import com.example.dao.SeatRepository;
import com.example.model.Schedule;
import com.example.model.Seat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author qianzhihao
 * @version 2018/7/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddSeat {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    public void addSeat(){
        for(Schedule s:scheduleRepository.findAll()){
            for(int i=1;i<=5;i++){
                for(int j=1;j<=15;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"A区",i,j,1280,0));
                }
            }
            for(int i=1;i<=4;i++){
                for(int j=1;j<=15;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"B区",i,j,980,0));
                }
            }
            for(int i=1;i<=8;i++){
                for(int j=1;j<=7;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"C1区",i,j,680,0));
                }
            }
            for(int i=1;i<=8;i++){
                for(int j=1;j<=7;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"C2区",i,j,680,0));
                }
            }
            for(int i=1;i<=5;i++){
                for(int j=1;j<=15;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"D区",i,j,380,0));
                }
            }
            for(int i=1;i<=8;i++){
                for(int j=1;j<=7;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"E1区",i,j,280,0));
                }
            }
            for(int i=1;i<=8;i++){
                for(int j=1;j<=7;j++){
                    seatRepository.save(new Seat(s.getSchedule_id(),"E2区",i,j,280,0));
                }
            }
        }
    }
}
