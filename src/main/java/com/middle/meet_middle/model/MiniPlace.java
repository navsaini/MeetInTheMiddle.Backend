package com.middle.meet_middle.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class MiniPlace {
    private String name;
    private String address;
    private String price;
    private double rating;
    private double latitude;
    private double longitude;

    public MiniPlace(String name, String address, double rating, String price, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + rating + " " + price + " " + latitude + " " + longitude;
    }
}
