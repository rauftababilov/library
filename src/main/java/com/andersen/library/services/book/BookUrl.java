package com.andersen.library.services.book;

public interface BookUrl {

    String RESOURCE_NAME = "/books";

    String ID = "/{bookId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
