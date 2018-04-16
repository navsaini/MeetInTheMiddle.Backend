package com.middle.meet_middle.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@JsonSerialize
@Data
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
