package com.andersen.library.services.book.impl;

import com.andersen.library.services.book.model.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BookServiceImpl.class)
interface BookMapper {

    BookDto toDto(Book entity);

    default void populateEmptyBook(Book book, BookDto dto) {
        book.setPublishYear(dto.getPublishYear());
        book.setAuthorIds(dto.getAuthorIds());
        book.setTitle(dto.getTitle());
        book.setPublishingHouseId(dto.getPublishingHouseId());
    }

    default void updateBookData(Book book, BookDto dto) {
        book.setPublishYear(dto.getPublishYear());
        book.getAuthorIds().clear();
        book.getAuthorIds().addAll(dto.getAuthorIds());
        book.setTitle(dto.getTitle());
        book.setPublishingHouseId(dto.getPublishingHouseId());
    }

}
