package com.middle.meet_middle.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class MiniPlace {
    private String name;
    private String address;
    private String price;
    String website;
    private double rating;
    private double latitude;
    private double longitude;

    public MiniPlace(String name, String address, double rating, String price, String website, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price = price;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + rating + " " + price + " " + website + " " + latitude + " " + longitude;
    }
}
