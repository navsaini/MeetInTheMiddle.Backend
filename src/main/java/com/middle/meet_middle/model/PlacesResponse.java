package com.middle.meet_middle.model;

import java.util.List;

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
