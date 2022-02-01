package br.coom.muler.dto;

import br.coom.muler.enumerated.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class CreateLoginDTO {

    @Email
    public String email;

    @Min(10)
    public String password;

    public Profile role;

}
