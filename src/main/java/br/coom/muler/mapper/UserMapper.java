package br.coom.muler.mapper;

import br.coom.muler.dto.UserDto;
import br.coom.muler.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login.email", target = "email")
    UserDto toDto(User user);
}
