package br.coom.muler.service;

import br.coom.muler.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    public User getById(Long id) {
        Optional<User> user = User.findByIdOptional(id);

        return user.orElseThrow(NotFoundException::new);
    }
}
