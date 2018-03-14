package com.middle.meet_middle.service;

import com.middle.meet_middle.model.GooglePlacesClient;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.List;

public class PlacesService {
    public GooglePlaces client = GooglePlacesClient.getInstance().client;

    public List<Place> findPlacesByQuery(String query) {
        return client.getPlacesByQuery(query);
    }

    // TODO: write findPlacesByCoordinates
}
