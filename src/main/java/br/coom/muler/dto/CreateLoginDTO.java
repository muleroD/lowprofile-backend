package br.coom.muler.dto;

import br.coom.muler.enumerated.Profile;

import javax.validation.constraints.Email;

public class CreateLoginDTO {

    @Email
    public String email;

    public String password;

    public Profile role;

}
