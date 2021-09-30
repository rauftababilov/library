package com.andersen.library.services.user;

import com.andersen.library.services.user.model.UserDto;

public interface UserService {

    UserDto getById(Long id, boolean allowDeleted);

    UserDto getByUsername(String username, boolean allowDeleted);

    UserDto create(UserDto dto);

    void softDelete(Long id);

}
