package com.andersen.library.services.record_keeping;

public interface RecordKeepingUrl {

    String RESOURCE_NAME = "/audit";

    String ID = "/{auditId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
