package com.andersen.library.services.book_audit;

public interface BookAuditUrl {

    String RESOURCE_NAME = "/book-audit";

    String ID = "/{auditId}";

    String GET = RESOURCE_NAME + ID;

    String FIND = RESOURCE_NAME;

    String CREATE = RESOURCE_NAME;

    String UPDATE = RESOURCE_NAME + ID;

    String DELETE = RESOURCE_NAME + ID;

}
