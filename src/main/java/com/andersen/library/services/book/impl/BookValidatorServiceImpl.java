package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorService;
import com.andersen.library.services.book.BookValidatorService;
import com.andersen.library.services.book_audit.BookAuditService;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import com.andersen.library.services.book_audit.model.BookState;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
class BookValidatorServiceImpl implements BookValidatorService {

    private final AuthorService authorService;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private BookAuditService bookAuditService;

    @Override
    public void throwIfAuthorsIncorrect(List<Long> authorIds) {
        authorIds.forEach(authorService::get);
    }

    @Override
    public void throwIfPublishingHouseIncorrect(Long publishingHouse) {
        //TODO
    }

    @Override
    public void throwIfPublishYearIncorrect(Integer publishYear) {
        if (Year.now().getValue() < publishYear) {
            throw ExceptionType.BOOK_PUBLISH_YEAR_INCORRECT.exception();
        }
    }

    @Override
    public void throwIfBookGiven(Long bookId) {
        BookAuditFilterDto filterDto = BookAuditFilterDto.builder()
                .bookState(BookState.GIVEN)
                .bookId(bookId)
                .build();

        if (bookAuditService.getAll(filterDto, Pageable.unpaged()).getSize() != 0) {
            throw ExceptionType.BOOK_GIVEN.exception();
        }
    }

    @Override
    public void throwIfBookDeleted(boolean deleted) {
        if (deleted) {
            throw ExceptionType.BOOK_DELETED.exception();
        }
    }

}
