package br.coom.muler.resource;

import br.coom.muler.entity.User;
import br.coom.muler.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    @GET
    @Path("/{id}")
    public User findById(@PathParam("id") Long id) {
        return service.getById(id);
    }
}
