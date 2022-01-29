package br.coom.muler.service;

import br.coom.muler.entity.Login;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class LoginService {

    public List<Login> listAll() {
        return Login.listAll();
    }

    public Login getById(Long id) {
        return Login.findById(id);
    }

    public Response create(Login login) {
        login.persist();
        return Response.created(URI.create("/auth/" + login.id)).build();
    }

    public Login updateById(Long id, Login body) {
        Login entity = Login.findById(id);

        if (entity == null)
            throw new NotFoundException();

        entity.email = body.email;
        entity.password = body.password;

        return entity;
    }

    public void deleteById(Long id) {
        Login entity = Login.findById(id);

        if (entity == null)
            throw new NotFoundException();

        entity.delete();
    }

    public Login findByEmail(String email) {
        Login entity = Login.findByEmail(email);

        if (entity == null)
            throw new NotFoundException();

        return entity;
    }
}
