package com.middle.meet_middle.endpoint;

import com.grum.geocalc.Point;
import com.middle.meet_middle.model.MiniPlace;
import com.middle.meet_middle.model.PlacesResponse;
import com.middle.meet_middle.service.PlacesService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import se.walkercrou.places.Place;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Component
@Path("/places")
public class PlacesEndpoint {

    @Autowired
    private PlacesService placesService;

    @CrossOrigin
    @GET
    @Path("/{startLat}/{startLong}/{endLat}/{endLong}/")
    @Produces(MediaType.APPLICATION_JSON)
    public PlacesResponse getPlaces(@PathParam("startLat") double startLat, @PathParam("startLong") double startLong,
                              @PathParam("endLat") double endLat, @PathParam("endLong") double endLong) {
        /*To Do: Place the list of location types sent from the front end into the ArrayList. For now, dummy
          have been placed.
         */
        ArrayList<String> locTypes = new ArrayList<>();
        locTypes.add("restaurant");
        locTypes.add("coffee");

        List<Place> places = placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, 500, locTypes);
        Point midPoint = placesService.getMidpointCoords(startLat, startLong, endLat, endLong);
        List<MiniPlace> miniPlaces = new ArrayList<>();
        for (Place p: places) {
            Place pDet = p.getDetails();
            MiniPlace mp = new MiniPlace(pDet.getName(), pDet.getAddress());
            miniPlaces.add(mp);
        }
        PlacesResponse pr = new PlacesResponse(miniPlaces, midPoint.latitude, midPoint.longitude);
        System.out.println(pr);
        return pr;
    }
}