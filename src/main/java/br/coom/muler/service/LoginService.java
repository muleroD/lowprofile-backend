package br.coom.muler.service;

import br.coom.muler.dto.CreateLoginDTO;
import br.coom.muler.entity.Login;
import br.coom.muler.enumerated.Profile;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@ApplicationScoped
public class LoginService {

    @Inject
    SecurityIdentity identity;

    public Response register(@Valid CreateLoginDTO body) {
        Optional<Login> entity = Login.findByEmail(body.email);

        if (entity.isPresent())
            return Response
                    .status(BAD_REQUEST.getStatusCode())
                    .entity("E-mail já cadastrado")
                    .build();

        if (!identity.hasRole(Profile._ADMIN))
            body.role = Profile.USER;

        Login.save(body);

        return Response.ok().build();
    }

    public Response remove(Long id) {
        if (Login.deleteById(id)) return Response.ok().build();

        return Response
                .status(BAD_REQUEST.getStatusCode())
                .entity(String.format("Login não encontrado com o id: %d", id))
                .build();
    }
}
