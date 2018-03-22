package com.middle.meet_middle.model;

import se.walkercrou.places.GooglePlaces;
import com.middle.meet_middle.model.MyGooglePlaces;
import com.middle.meet_middle.model.MyRequestHandler;

public class GooglePlacesClient {

    private static GooglePlacesClient instance;
    public GooglePlaces client;

    private GooglePlacesClient() {
        client = new MyGooglePlaces(System.getenv("PLACES_KEY"), new MyRequestHandler());
    }

    public static GooglePlacesClient getInstance() {
        if (instance == null) {
            instance = new GooglePlacesClient();
        }
        return instance;
    }
}
