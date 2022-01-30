package br.coom.muler.resource;

import br.coom.muler.entity.Login;
import br.coom.muler.enumerated.Profile;
import br.coom.muler.service.LoginService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService service;

    @POST
    @Path("/register")
    @PermitAll
    @Transactional
    public Response register(@Context SecurityContext securityContext, Login login) {
        return service.register(securityContext, login);
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed(Profile._ADMIN)
    @Transactional
    public Response remove(@PathParam("id") Long id) {
        return service.remove(id);
    }
}
