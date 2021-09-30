package com.andersen.library.services.book;

import lombok.Data;

import java.util.Set;

@Data
public class BookFilterDto {

    private Set<Long> authorId;

    private Set<Long> publishingHouseId;

}
