package com.andersen.library.services.book_audit.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class BookAuditDto {

    @Null
    private Long id;

    @NotNull
    @Min(1)
    private Long bookId;

    @NotNull
    @Min(1)
    private Long clientId;

    @NotNull
    private BookState bookState;

}
