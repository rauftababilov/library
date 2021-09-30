package com.andersen.library.services.client.impl;

import com.andersen.library.exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ClientValidatorService {

    private final ClientRepository repository;

    public void throwIfClientAlreadyExists(String clientName) {
        if (repository.existsByFullName(clientName)) {
            throw ExceptionType.CLIENT_ALREADY_EXISTS.exception();
        }
    }

    public void throwIfClientNameChangeNotAllowed(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            throwIfClientAlreadyExists(newName);
        }
    }

}
