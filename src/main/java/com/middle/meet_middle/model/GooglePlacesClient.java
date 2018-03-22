package com.middle.meet_middle.model;

import se.walkercrou.places.GooglePlaces;

public class GooglePlacesClient {

    private static GooglePlacesClient instance;
    public GooglePlaces client;

    private GooglePlacesClient() {
        client = new GooglePlaces(System.getenv("PLACES_KEY"), new MyRequestHandler());
    }

    public static GooglePlacesClient getInstance() {
        if (instance == null) {
            instance = new GooglePlacesClient();
        }
        return instance;
    }
}
