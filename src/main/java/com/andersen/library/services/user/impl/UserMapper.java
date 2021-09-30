package com.andersen.library.services.user.impl;

import com.andersen.library.services.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserMapper {

    UserDto toDto(User entity);

}
