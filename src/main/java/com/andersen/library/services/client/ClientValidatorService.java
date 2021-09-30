package com.andersen.library.services.client;

public interface ClientValidatorService {

    void throwIfClientAlreadyExists(String clientName);

    void throwIfClientNameChangeNotAllowed(String oldName, String newName);

    void throwIfClientHasGivenBook(Long clientId);

    void throwIfClientDeleted(boolean deleted);

}
