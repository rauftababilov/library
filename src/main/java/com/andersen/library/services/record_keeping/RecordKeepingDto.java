package com.andersen.library.services.record_keeping;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class RecordKeepingDto {

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
