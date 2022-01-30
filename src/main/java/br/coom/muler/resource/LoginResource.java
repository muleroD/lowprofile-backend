package br.coom.muler.resource;

import br.coom.muler.entity.Login;
import br.coom.muler.service.LoginService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService service;

    @GET
    public List<Login> list() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Login getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    @Path("/register")
    @Transactional
    public Response create(Login login) {
        return service.create(login);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Login update(@PathParam("id") Long id, Login body) {
        return service.updateById(id, body);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.deleteById(id);
    }

    @GET
    @Path("/search/{email}")
    public Login search(@PathParam("email") String email) {
        return service.findByEmail(email);
    }
}
