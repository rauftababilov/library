package com.andersen.library.services.user;

public interface UserUrl {

    String RESOURCE_NAME = "/users";

    String ID = "/{userId}";

    String GET = RESOURCE_NAME + ID;

    String CREATE = RESOURCE_NAME;

    String DELETE = RESOURCE_NAME + ID;

}
