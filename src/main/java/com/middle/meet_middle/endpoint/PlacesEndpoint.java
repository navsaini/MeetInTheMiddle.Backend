package com.middle.meet_middle.endpoint;

import com.grum.geocalc.Point;
import com.middle.meet_middle.model.MiniPlace;
import com.middle.meet_middle.model.PlacesResponse;
import com.middle.meet_middle.service.PlacesService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GET
    @Path("/{startLat}/{startLong}/{endLat}/{endLong}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaces(@PathParam("startLat") double startLat, @PathParam("startLong") double startLong,
                              @PathParam("endLat") double endLat, @PathParam("endLong") double endLong) {

        List<Place> places = placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, 500);
        Point midPoint = placesService.getMidpointCoords(startLat, startLong, endLat, endLong);
        List<MiniPlace> miniPlaces = new ArrayList<>();
        for (Place p: places) {
            Place pDet = p.getDetails();
            System.out.println(pDet.getName());
            MiniPlace mp = new MiniPlace(pDet.getName(), pDet.getAddress());
            miniPlaces.add(mp);
        }
        PlacesResponse pr = new PlacesResponse(miniPlaces, midPoint.latitude, midPoint.longitude);
        return Response.ok()
                .entity(pr)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }
}