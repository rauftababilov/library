package com.andersen.library.services.client;

public interface ClientUrl {

    String RESOURCE_NAME = "/clients";

    String ID = "/{clientId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
