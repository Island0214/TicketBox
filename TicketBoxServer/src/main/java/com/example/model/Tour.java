package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author keenan on 2018/6/27
 */
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tour_id;

    private String artist;

    private String tourName;

    public Tour() {
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
}
