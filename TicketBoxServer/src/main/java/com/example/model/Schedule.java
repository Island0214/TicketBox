package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/18.
 */
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_id;

    private String intro;

    private String schedule;

    private String artist;

    private String time;

    private String poster;

    public Schedule() {
    }

    public Schedule(int schedule_id, String intro, String schedule, String artist, String time, String poster) {
        this.schedule_id = schedule_id;
        this.intro = intro;
        this.schedule = schedule;
        this.artist = artist;
        this.time = time;
        this.poster = poster;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedule_id=" + schedule_id +
                ", intro='" + intro + '\'' +
                ", schedule='" + schedule + '\'' +
                ", artist='" + artist + '\'' +
                ", time='" + time + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
