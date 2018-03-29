package com.middle.meet_middle.endpoint;

import com.middle.meet_middle.model.User;
import com.middle.meet_middle.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("/users")
public class Endpoint {

    @Autowired
    private UserService userService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventVersion1(@PathParam("id") String id) {
        return Response.ok() //200
                .entity(userService.findById(id))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
}