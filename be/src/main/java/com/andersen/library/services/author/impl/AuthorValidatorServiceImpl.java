package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthorValidatorServiceImpl implements AuthorValidatorService {

    private final AuthorRepository repository;

    @Override
    public void throwIfAuthorAlreadyExists(String clientName) {
        if (repository.existsByFullNameAndDeletedIsFalse(clientName)) {
            throw ExceptionType.AUTHOR_ALREADY_EXISTS.exception();
        }
    }

    @Override
    public void throwIfAuthorNameChangeNotAllowed(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            throwIfAuthorAlreadyExists(newName);
        }
    }

    @Override
    public void throwIfAuthorDeleted(boolean deleted) {
        if (deleted) {
            throw ExceptionType.AUTHOR_DELETED.exception();
        }
    }

}
