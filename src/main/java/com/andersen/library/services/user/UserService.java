package com.andersen.library.services.user;

import com.andersen.library.services.user.dto.UserDto;

public interface UserService {

    UserDto getById(Long id, boolean allowDeleted);

    UserDto getByUsername(String username, boolean allowDeleted);

    UserDto create(UserDto dto);

    UserDto softDelete(Long id);

}
