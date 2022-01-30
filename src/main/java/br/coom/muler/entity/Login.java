package br.coom.muler.entity;

import br.coom.muler.enumerated.Profile;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;
import java.util.Optional;

@Entity
@UserDefinition
public class Login extends PanacheEntity {

    @Username
    public String email;
    @Password
    public String password;
    @Roles
    public String role;

    public static Optional<Login> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public static void save(Login login) {
        Login entity = new Login();

        entity.email = login.email;
        entity.password = BcryptUtil.bcryptHash(login.password);
        entity.role = Profile.valueOf(login.role).name();

        entity.persist();
    }
}
