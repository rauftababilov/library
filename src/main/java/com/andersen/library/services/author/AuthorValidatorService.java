package com.andersen.library.services.author;

public interface AuthorValidatorService {

    void throwIfAuthorDeleted(boolean deleted);

}
