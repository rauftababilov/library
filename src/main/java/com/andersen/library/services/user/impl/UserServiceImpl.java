package com.andersen.library.services.user.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.security.PredefinedRole;
import com.andersen.library.services.user.UserService;
import com.andersen.library.services.user.UserValidatorService;
import com.andersen.library.services.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        Role role = new Role();
        role.setId(PredefinedRole.ADMIN.getId());
        role.setName(PredefinedRole.ADMIN);
        user.getRoles().add(role);

        user = repository.save(user);

        return mapper.toDto(user);
    }

    @Override
    public UserDto softDelete(Long id) {
        User user = repository.findById(id).orElseThrow(ExceptionType.USER_NOT_FOUND::exception);

        if (user.getRoles().stream().anyMatch(x -> x.getName().equals(PredefinedRole.ROOT))) {
            throw ExceptionType.ROOT_USER_DELETE.exception();
        }

        user.setDeleted(true);

        user = repository.save(user);

        return mapper.toDto(user);
    }

}
