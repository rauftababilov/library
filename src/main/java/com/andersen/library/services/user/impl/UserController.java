package com.andersen.library.services.user.impl;

import com.andersen.library.services.user.UserService;
import com.andersen.library.services.user.UserUrl;
import com.andersen.library.services.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping(UserUrl.GET)
    public UserDto get(@PathVariable Long userId) {
        return userService.getById(userId, true);
    }

    @PostMapping(UserUrl.CREATE)
    public UserDto create(@Valid @RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @DeleteMapping(UserUrl.DELETE)
    public void delete(@PathVariable Long userId) {
        userService.softDelete(userId);
    }


}
