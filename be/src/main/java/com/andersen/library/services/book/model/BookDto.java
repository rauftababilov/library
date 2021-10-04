package com.andersen.library.services.book.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class BookDto {

    @Null
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @Min(1)
    private Integer publishYear;

    @NotNull
    @Min(1)
    private Long publishingHouseId;

    @NotEmpty
    private List<Long> authorIds = new LinkedList<>();

    @AssertFalse
    private boolean deleted;

}
