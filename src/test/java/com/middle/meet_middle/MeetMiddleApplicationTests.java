package com.middle.meet_middle;

import com.grum.geocalc.Point;
import com.middle.meet_middle.service.PlacesService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.walkercrou.places.Place;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetMiddleApplicationTests {

	static PlacesService placesService;

	@BeforeClass
	public static void runBeforeClass()
	{
        placesService = new PlacesService();
	}

	@Test
    // test two locations near each other
    public void testFindPlaces0() {
	    double startLat = 30.2910019;
	    double startLong = -97.7423068;

	    double endLat = 30.2882421;
	    double endLong = -97.73531109999999;

	    String[] poiTypes = new String[1];
        poiTypes[0] = "restauraunt";


	    List<Place> places = placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, poiTypes);
	    assertTrue(places.size() > 0);
    }

    @Test
    // test two locations very far away
    public void testFindPlaces1() {
        double startLat = 33.01984309999999;
        double startLong = -96.69888559999998;

        double endLat = 49.2827291;
        double endLong = -123.12073750000002;

        String[] poiTypes = new String[1];
        poiTypes[0] = "restauraunt";

        List<Place> places = placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, poiTypes);
        assertTrue(places.size() > 0);
    }

    @Test
    // test invalid latitude/longitude
    public void testFindPlaces2() {
        double startLat = 90.312351332;
        double startLong = -96.69888559999998;

        double endLat = 49.2827291;
        double endLong = -123.12073750000002;

        String[] poiTypes = new String[1];
        poiTypes[0] = "restauraunt";

	    try {
            placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, poiTypes);
        } catch (IllegalArgumentException e) {
	        return;
        }

        fail();
    }

    @Test
    // comparing to results from http://www.movable-type.co.uk/scripts/latlong.html
    public void testMidpoint() {
        double startLat = 51.4843774;
        double startLong = -0.2912044;

        double endLat = 51.4613418;
        double endLong = -0.3035466;

        Point midpoint = placesService.getMidpointCoords(startLat, startLong, endLat, endLong);
        assertTrue(midpoint.latitude == 51.47285976194266);
        assertTrue(midpoint.longitude == -0.2973770580524634);
    }



}
