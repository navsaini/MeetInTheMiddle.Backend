package com.middle.meet_middle.service;

import com.middle.meet_middle.model.GooglePlacesClient;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Param;

import com.grum.geocalc.Point;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;



import java.util.List;

@Service
public class PlacesService {
    public GooglePlaces client = GooglePlacesClient.getInstance().client;

    public List<Place> findPlacesByQuery(String query) {
        return client.getPlacesByQuery(query);
    }

    public List<Place> findPlacesByCoordinates(double startLat, double startLong, double endLat, double endLong, double radius) {
        Point loc1 = Point.at(Coordinate.fromDegrees(startLat), Coordinate.fromDegrees(startLong));
        Point loc2 = Point.at(Coordinate.fromDegrees(endLat), Coordinate.fromDegrees(endLong));
        Point midPoint = EarthCalc.midPoint(loc1, loc2);
        try {
            return client.getNearbyPlaces(midPoint.latitude, midPoint.longitude, radius, Param.name("keyword").value("restaurants"));
        }
        catch (Exception e){
            System.out.println("No places found");
            return null;
        }

    }

    public Point getMidpointCoords(double startLat, double startLong, double endLat, double endLong) {
        Point loc1 = Point.at(Coordinate.fromDegrees(startLat), Coordinate.fromDegrees(startLong));
        Point loc2 = Point.at(Coordinate.fromDegrees(endLat), Coordinate.fromDegrees(endLong));
        Point midPoint = EarthCalc.midPoint(loc1, loc2);
        return midPoint;
    }
}
