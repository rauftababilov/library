package com.andersen.library.services.author;

import lombok.Data;

import java.util.Set;

@Data
public class AuthorFilterDto {
    private String name;
    private Set<Long> bookId;
}
