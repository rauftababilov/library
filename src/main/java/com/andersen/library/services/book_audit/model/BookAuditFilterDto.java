package com.andersen.library.services.book_audit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookAuditFilterDto {

    private Long clientId;

    private Long bookId;

    private BookState bookState;

}
