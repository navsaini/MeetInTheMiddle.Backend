package com.middle.meet_middle.endpoint;

import com.grum.geocalc.Point;
import com.middle.meet_middle.model.MiniPlace;
import com.middle.meet_middle.model.PlacesResponse;
import com.middle.meet_middle.service.PlacesService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import se.walkercrou.places.Place;
import se.walkercrou.places.Price;

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
    @Path("/{startLat}/{startLong}/{endLat}/{endLong}/{locTypes}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlacesResponse getPlaces(@PathParam("startLat") double startLat, @PathParam("startLong") double startLong,
                              @PathParam("endLat") double endLat, @PathParam("endLong") double endLong,
                                    @PathParam ("locTypes") String locTypes) {
        /*To Do: Place the list of location types sent from the front end into the ArrayList. For now, dummy
          have been placed.
         */
        String[] poiTypes = locTypes.split(",");

        List<Place> places = placesService.findPlacesByCoordinates(startLat, startLong, endLat, endLong, poiTypes);
        Point midPoint = placesService.getMidpointCoords(startLat, startLong, endLat, endLong);
        List<MiniPlace> miniPlaces = new ArrayList<>();
        for (Place p: places) {
            Place pDet = PlacesService.getDetails(p);
            Price price = p.getPrice();
            String priceStr = priceToDollars(price);
            System.out.println(pDet.getName());
            MiniPlace mp = new MiniPlace(pDet.getName(), pDet.getAddress(), p.getRating(), priceStr, p.getLatitude(), p.getLongitude());
            miniPlaces.add(mp);
        }
        PlacesResponse pr = new PlacesResponse(miniPlaces, midPoint.latitude, midPoint.longitude);
        System.out.println(pr);
        return pr;
    }

    private String priceToDollars(Price price) {
        String priceValue = price.toString();

        if(priceValue.equals("FREE")) {
            return "FREE";
        }
        else if(priceValue.equals("INEXPENSIVE")) {
            return "$";
        }
        else if(priceValue.equals("MODERATE")) {
            return "$$";
        } else if (priceValue.equals("EXPENSIVE")) {
            return "$$$";
        }
        else if (priceValue.equals("VERY_EXPENSIVE")) {
            return "$$$$";
        }
        else {
            return "";
        }
    }

}