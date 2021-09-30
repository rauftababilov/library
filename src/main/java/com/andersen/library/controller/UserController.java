package com.andersen.library.controller;

import com.andersen.library.controller.dto.UserDto;
import com.andersen.library.jpa.domain.User;
import com.andersen.library.mapper.UserDtoMapper;
import com.andersen.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @GetMapping("/auth")
    public Principal getUserInfo(Principal principal) {
        return principal;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDto userDto = userDtoMapper.toUserDto(user);
        return userDto;
    }
}
