package br.coom.muler.resource;

import br.coom.muler.entity.Login;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @GET
    public List<Login> list() {
        return Login.listAll();
    }

    @GET
    @Path("/{id}")
    public Login getById(@PathParam("id") Long id) {
        return Login.findById(id);
    }

    @POST
    @Transactional
    public Response create(Login login) {
        login.persist();
        return Response.created(URI.create("/auth/" + login.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Login update(@PathParam("id") Long id, Login body) {
        Login entity = Login.findById(id);

        if (entity == null)
            throw new NotFoundException();

        entity.email = body.email;
        entity.password = body.password;

        return entity;
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Login entity = Login.findById(id);

        if (entity == null)
            throw new NotFoundException();

        entity.delete();
    }

    @GET
    @Path("/search/{email}")
    public Login search(@PathParam("email") String email) {
        Login entity = Login.findByEmail(email);

        if (entity == null)
            throw new NotFoundException();

        return entity;
    }

    @GET
    @Path("/count")
    public Long count() {
        return Login.count();
    }

}
