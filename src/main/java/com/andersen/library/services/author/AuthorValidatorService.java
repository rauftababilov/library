package com.andersen.library.services.author;

public interface AuthorValidatorService {

    void throwIfAuthorAlreadyExists(String clientName);

    void throwIfAuthorNameChangeNotAllowed(String oldName, String newName);

    void throwIfAuthorDeleted(boolean deleted);

}
