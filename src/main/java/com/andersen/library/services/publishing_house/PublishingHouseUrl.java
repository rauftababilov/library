package com.andersen.library.services.publishing_house;

public interface PublishingHouseUrl {

    String RESOURCE_NAME = "/publishing-houses";

    String ID = "/{publishingHouseId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
