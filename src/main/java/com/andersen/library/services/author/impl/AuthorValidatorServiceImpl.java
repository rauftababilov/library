package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorValidatorService;
import org.springframework.stereotype.Service;

@Service
class AuthorValidatorServiceImpl implements AuthorValidatorService {

    @Override
    public void throwIfAuthorDeleted(boolean deleted) {
        if (deleted) {
            throw ExceptionType.AUTHOR_DELETED.exception();
        }
    }

}
