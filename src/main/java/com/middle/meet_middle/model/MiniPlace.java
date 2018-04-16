package com.middle.meet_middle.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@Data
public class MiniPlace {
    private String name;
    private String address;

    public MiniPlace(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + address;
    }
}
