package br.coom.muler.resource;

import br.coom.muler.dto.CreateLoginDto;
import br.coom.muler.enumerated.Profile;
import br.coom.muler.service.LoginService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public Response register(@Valid CreateLoginDto login) {
        return service.register(login);
    }

    @POST
    @RolesAllowed({Profile._ADMIN, Profile._USER})
    @Transactional
    public Response login() {
        return service.login();
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed(Profile._ADMIN)
    @Transactional
    public Response remove(@PathParam("id") Long id) {
        return service.remove(id);
    }
}
