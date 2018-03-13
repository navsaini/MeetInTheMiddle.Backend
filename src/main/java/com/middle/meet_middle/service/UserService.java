package com.middle.meet_middle.service;

import com.middle.meet_middle.model.GooglePlacesClient;
import com.middle.meet_middle.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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
        return users.get(id);
    }
}
