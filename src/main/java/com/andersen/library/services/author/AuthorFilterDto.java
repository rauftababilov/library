package com.andersen.library.services.author;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorFilterDto {
    private String name;
    private List<Long> bookId;
}
