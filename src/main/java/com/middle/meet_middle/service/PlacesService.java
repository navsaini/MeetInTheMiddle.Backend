package com.middle.meet_middle.service;

import com.middle.meet_middle.model.GooglePlacesClient;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Param;

import com.grum.geocalc.Point;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;

import java.util.*;

@Service
public class PlacesService {
    private int MAX_RESULTS = 12;
    private static Map<String, Place> cachedDetails;
    private GooglePlaces client = GooglePlacesClient.getInstance().client;

    public PlacesService() {
        cachedDetails = new HashMap<>();
    }

    public static Place getDetails(Place p) {
        if (cachedDetails.containsKey(p.getPlaceId())) {
            System.out.println("Cached response used");
            return cachedDetails.get(p.getPlaceId());
        } else {
            Place placeWithDetails = p.getDetails();
            cachedDetails.put(p.getPlaceId(), placeWithDetails);
            return placeWithDetails;
        }
    }

    public List<Place> findPlacesByCoordinates(double startLat, double startLong, double endLat, double endLong, String[] poiTypes) {
        if (startLat < -90 || startLat > 90 ||
                startLong < -180 || startLong > 180 ||
                endLat < -90 || endLat > 90 ||
                endLong < -180 || endLong > 180) throw new IllegalArgumentException();

        int limit = calculateLimit(poiTypes);
        Point loc1 = Point.at(Coordinate.fromDegrees(startLat), Coordinate.fromDegrees(startLong));
        Point loc2 = Point.at(Coordinate.fromDegrees(endLat), Coordinate.fromDegrees(endLong));
        Point midPoint = EarthCalc.midPoint(loc1, loc2);

        List<Place> finalResult = new ArrayList<>();
        for(String locType : poiTypes) {
            try {
                finalResult.addAll(client.getNearbyPlacesRankedByDistance(midPoint.latitude, midPoint.longitude, limit, Param.name("keyword").value(locType)));
            }
            catch (Exception e){
                System.out.println("No places found");
            }
        }

        return sortByDistance(finalResult, midPoint);
    }

    public Point getMidpointCoords(double startLat, double startLong, double endLat, double endLong) {
        Point loc1 = Point.at(Coordinate.fromDegrees(startLat), Coordinate.fromDegrees(startLong));
        Point loc2 = Point.at(Coordinate.fromDegrees(endLat), Coordinate.fromDegrees(endLong));
        Point midPoint = EarthCalc.midPoint(loc1, loc2);
        return midPoint;
    }

    /* Assumed 4 different location types: parks, bars, coffee shops, parks. */
    private int calculateLimit(String[] poiTypes) {
        return MAX_RESULTS/poiTypes.length;
    }

    private List<Place> sortByDistance(List<Place> finalResult, Point midPoint) {
        List<Double> distance = new ArrayList<>();
        Map<Double, List<Place>> distanceMap;

        for(Place place : finalResult) {
            Point p = Point.at(Coordinate.fromDegrees(place.getLatitude()), Coordinate.fromDegrees(place.getLongitude()));
            distance.add(EarthCalc.gcdDistance(p, midPoint));
        }

        distanceMap = generateDistancePlaceMap(finalResult, distance);
        Collections.sort(distance);

        finalResult = sortResults(distance, distanceMap);

        return finalResult;
    }

    private Map<Double, List<Place>> generateDistancePlaceMap(List<Place> finalResult, List<Double> distance) {
        Map<Double, List<Place>> distanceMap = new HashMap<>();
        int finalResultIndex = 0;

        for(Double dist : distance) {
            if(!distanceMap.containsKey(dist)) {
                List<Place> newList = new ArrayList<>();
                newList.add(finalResult.get(finalResultIndex));
                distanceMap.put(dist, newList);
            }
            else {
                List<Place> oldList = distanceMap.get(dist);
                oldList.add(finalResult.get(finalResultIndex));
                distanceMap.put(dist, oldList);
            }
            finalResultIndex++;
        }

        return distanceMap;
    }

    private List<Place> sortResults(List<Double> distance, Map<Double, List<Place>> distanceMap) {
        List<Place> finalResult = new ArrayList<>();

        for(Double dist : distance) {
            List<Place> placeAtDistance = distanceMap.get(dist);

            for(Place place : placeAtDistance) {
                finalResult.add(place);
            }
        }
        return finalResult;
    }
}
