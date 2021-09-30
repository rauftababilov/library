package com.andersen.library.services.book.impl;

import com.andersen.library.services.book.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BookMapper {

    BookDto toDto(Book entity);

}
