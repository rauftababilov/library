package com.andersen.library.services.user.impl;

import com.andersen.library.services.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToString")
    UserDto toDto(User entity);

    @Named("rolesToString")
    default Set<String> rolesToString(List<Role> roles) {
        return roles.stream().map(Role::getName).map(Enum::name).collect(Collectors.toSet());
    }

}
