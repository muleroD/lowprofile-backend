package br.coom.muler.service;

import br.coom.muler.dto.CreateLoginDto;
import br.coom.muler.entity.Login;
import br.coom.muler.entity.User;
import br.coom.muler.enumerated.Profile;
import br.coom.muler.mapper.UserMapper;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@ApplicationScoped
public class LoginService {

    @Inject
    SecurityIdentity identity;

    @Inject
    UserMapper userMapper;

    public Response register(CreateLoginDto body) {
        Optional<Login> optionalLogin = Login.findByEmail(body.email);

        if (optionalLogin.isPresent())
            return Response
                    .status(BAD_REQUEST.getStatusCode())
                    .entity("E-mail já cadastrado")
                    .build();

        if (!identity.hasRole(Profile._ADMIN))
            body.role = Profile.USER;

        Login entity = Login.save(body);

        User.save(entity);

        return Response.ok().build();
    }

    public Response login() {
        Optional<User> optionalUser = User.findByEmail(identity.getPrincipal().getName());

        if (optionalUser.isEmpty())
            return Response
                    .status(BAD_REQUEST.getStatusCode())
                    .entity("E-mail já cadastrado")
                    .build();

        return Response.ok(userMapper.toDto(optionalUser.get())).build();
    }

    public Response remove(Long id) {
        if (Login.deleteById(id)) return Response.ok().build();

        return Response
                .status(BAD_REQUEST.getStatusCode())
                .entity(String.format("Login não encontrado com o id: %d", id))
                .build();
    }
}
