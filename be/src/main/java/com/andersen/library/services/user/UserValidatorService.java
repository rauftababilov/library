package com.andersen.library.services.user;

public interface UserValidatorService {

    void throwIfUserAlreadyExists(String username);

}
