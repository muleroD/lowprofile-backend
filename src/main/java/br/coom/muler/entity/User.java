package br.coom.muler.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Entity
public class User extends PanacheEntity {

    public String name;
    public String bio;
    public String phone;
    public byte[] photo;

    @ManyToOne
    public Login login;

    public void setLogin(Login login) {
        this.login = login;
    }

    public static Optional<User> findByEmail(String name) {
        return find("login.email", name).firstResultOptional();
    }

    public static void save(Login entity) {
        User user = new User();
        user.setLogin(entity);

        user.persist();
    }
}
