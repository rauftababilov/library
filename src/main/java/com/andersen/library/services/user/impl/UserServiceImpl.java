package com.andersen.library.services.user.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.user.UserService;
import com.andersen.library.services.user.UserValidatorService;
import com.andersen.library.services.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    private final UserValidatorService validatorService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getById(Long id, boolean allowDeleted) {
        UserDto userDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.USER_NOT_FOUND::exception);

        if (!allowDeleted && userDto.isDeleted()) {
            throw ExceptionType.USER_DELETED.exception();
        }

        return userDto;
    }

    @Override
    public UserDto getByUsername(String username, boolean allowDeleted) {
        UserDto userDto = repository.findByUsername(username)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.USER_NOT_FOUND::exception);

        if (!allowDeleted && userDto.isDeleted()) {
            throw ExceptionType.USER_DELETED.exception();
        }

        return userDto;
    }

    @Override
    public UserDto create(UserDto dto) {
        validatorService.throwIfUserAlreadyExists(dto.getUsername());

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user = repository.save(user);

        return mapper.toDto(user);
    }

    @Override
    public UserDto softDelete(Long id) {
        User user = repository.findById(id).orElseThrow(ExceptionType.USER_NOT_FOUND::exception);

        user.setDeleted(true);

        user = repository.save(user);

        return mapper.toDto(user);
    }

}
