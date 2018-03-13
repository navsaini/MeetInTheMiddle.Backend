package com.middle.meet_middle.model;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.List;

public class GooglePlacesClient {

    private static GooglePlacesClient instance;
    private GooglePlaces client;

    private GooglePlacesClient() {
        client = new GooglePlaces(System.getenv("PLACES_KEY"));
    }

    // TODO: implement google places methods in here as non-static

    public static GooglePlacesClient getInstance() {
        if (instance == null) {
            instance = new GooglePlacesClient();
        }
        return instance;
    }

    public List<Place> findPlaces(String query) {
        return client.getPlacesByQuery(query);
    }
}
