package com.example.bean;

import java.util.List;

/**
 * @author keenan on 2018/6/27
 */
public class TourBean {
    private int tourId;
    private String artist;
    private String tourName;
    private List<TourCityBean> cities;

    public TourBean(int tourId, String artist, String tourName, List<TourCityBean> cities) {
        this.tourId = tourId;
        this.artist = artist;
        this.tourName = tourName;
        this.cities = cities;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
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

    public List<TourCityBean> getCities() {
        return cities;
    }

    public void setCities(List<TourCityBean> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "TourBean{" +
                "tourId=" + tourId +
                ", artist='" + artist + '\'' +
                ", tourName='" + tourName + '\'' +
                ", cities=" + cities +
                '}';
    }
}
