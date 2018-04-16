package com.middle.meet_middle.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class PlacesResponse {
    List<MiniPlace> places;
    double midLat;
    double midLong;

    public PlacesResponse(List<MiniPlace> places, double midLat, double midLong) {
        this.places = places;
        this.midLat = midLat;
        this.midLong = midLong;
    }

    @Override
    public String toString() {
        return places.toString() + " " + midLat + " " + midLong;
    }
}
