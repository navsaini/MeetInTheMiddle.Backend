package com.middle.meet_middle.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class MiniPlace {
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public MiniPlace(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + latitude + " " + longitude;
    }
}
