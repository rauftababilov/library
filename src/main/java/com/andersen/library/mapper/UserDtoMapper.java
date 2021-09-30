package com.andersen.library.mapper;

import com.andersen.library.controller.dto.UserDto;
import com.andersen.library.jpa.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
//    @Mappings({
//            @Mapping(target="id", source="user.id"),
//            @Mapping(target="username", source="user.username")
//    })
    UserDto toUserDto(User user);
}
