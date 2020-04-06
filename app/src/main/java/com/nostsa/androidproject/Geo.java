package com.nostsa.androidproject;

public class Geo {

    float lat;
    float lng;

    public Geo() {
    }

    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
