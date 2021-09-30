package com.andersen.library.services.author.model;

import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class AuthorDto {

    @Null
    private Long id;

    @NotBlank
    private String fullName;

    @AssertFalse
    private boolean deleted;

}
