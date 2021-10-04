package com.andersen.library.services.author;

public interface AuthorUrl {

    String RESOURCE_NAME = "/authors";

    String ID = "/{authorId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
