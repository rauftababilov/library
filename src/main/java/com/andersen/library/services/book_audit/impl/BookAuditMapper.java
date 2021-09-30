package com.andersen.library.services.book_audit.impl;

import com.andersen.library.services.book_audit.model.BookAuditDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BookAuditMapper {

    BookAuditDto toDto(BookAudit entity);

    default void populateEntityDataByDto(BookAudit bookAudit, BookAuditDto dto) {
        bookAudit.setBookId(dto.getBookId());
        bookAudit.setBookState(dto.getBookState());
        bookAudit.setClientId(dto.getClientId());

    }

}
