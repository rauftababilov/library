package com.andersen.library.services.book.impl;

import com.andersen.library.services.book.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookServiceImpl.class)
interface BookMapper {

    BookDto toDto(Book entity);

}
