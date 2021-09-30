package com.andersen.library.services.user.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.user.UserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserValidatorServiceImpl implements UserValidatorService {

    private final UserRepository repository;

    @Override
    public void throwIfUserAlreadyExists(String username) {
        if (repository.existsByUsernameAndDeletedIsFalse(username)) {
            throw ExceptionType.USER_ALREADY_EXISTS.exception();
        }
    }

}
