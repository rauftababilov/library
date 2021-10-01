package com.andersen.library.services.user;

import com.andersen.library.services.user.model.UserDto;

public interface UserService {

    /**
     * Find user by id
     *
     * @param id id user
     * @param allowDeleted delete flag
     * @return userDto
     */
    UserDto getById(Long id, boolean allowDeleted);

    /**
     * Find user by username
     *
     * @param username username
     * @param allowDeleted delete flag
     * @return userDto
     */
    UserDto getByUsername(String username, boolean allowDeleted);

    /**
     * Add user to DB
     *
     * @param dto usertDto
     * @return usertDto
     */
    UserDto create(UserDto dto);

    /**
     * Delete user by id from DB
     *
     * @param id id user
     */
    void softDelete(Long id);

}
