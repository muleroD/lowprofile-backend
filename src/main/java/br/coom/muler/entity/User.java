package br.coom.muler.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends PanacheEntity {

    public String name;
    public String bio;
    public String phone;
    public byte[] photo;

    @ManyToOne
    public Login login;
}
