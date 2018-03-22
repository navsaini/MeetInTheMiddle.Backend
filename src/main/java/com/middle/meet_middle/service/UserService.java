package com.middle.meet_middle.service;

import com.middle.meet_middle.model.GooglePlacesClient;
import com.middle.meet_middle.model.User;
import org.springframework.stereotype.Service;
import se.walkercrou.places.Place;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class UserService {
    private Map<String, User> users;

    @PostConstruct
    private void loadUser() {
        users = new HashMap<>();
        users.put("1", new User("Tom", 20));
        users.put("2", new User("Jerry", 19));
    }

    public User findById(String id) {
        PlacesService services = new PlacesService();
        List<Place> list = services.findPlacesByCoordinates(33.033302, -96.841446, 33.046375, -96.83556, 500);
        for(Place pl : list) {
           Place pl_detailed = pl.getDetails();
           System.out.println("Name: " + pl_detailed.getName());
        }
        return users.get(id);
    }
}
