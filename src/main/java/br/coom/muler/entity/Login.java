package br.coom.muler.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Login extends PanacheEntity {

    public String email;
    public String password;

    public static Login findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
