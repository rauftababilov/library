package com.andersen.library.services.author;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.LinkedList;
import java.util.List;

@Data
public class AuthorDto {

    @Null
    private Long id;

    @NotBlank
    private String fullName;

    @NotEmpty
    private List<Long> bookIds = new LinkedList<>();

}
