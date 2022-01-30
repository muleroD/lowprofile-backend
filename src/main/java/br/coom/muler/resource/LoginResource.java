package br.coom.muler.resource;

import br.coom.muler.entity.Login;
import br.coom.muler.service.LoginService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response register(Login login) {
        return service.register(login);
    }

    @DELETE
    @Path("/remove/{id}")
    @PermitAll
    @Transactional
    public Response remove(@PathParam("id") Long id) {
        return service.remove(id);
    }
}
