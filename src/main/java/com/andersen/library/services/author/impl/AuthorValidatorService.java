package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorFilterDto;
import com.andersen.library.services.author.AuthorService;
import com.andersen.library.services.publishing_house.PublishingHouseService;
import com.andersen.library.services.record_keeping.BookState;
import com.andersen.library.services.record_keeping.RecordKeepingFilterDto;
import com.andersen.library.services.record_keeping.RecordKeepingService;
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
public class AuthorValidatorService {

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private AuthorService authorService;

    private AuthorFilterDto authorFilterDto;

    void throwIfBooksIncorrect(List<Long> bookIds) {
        authorFilterDto = AuthorFilterDto.builder()
                .bookId(bookIds)
                .build();

        if(authorService.findAllByFilter(authorFilterDto, Pageable.unpaged()).getSize() == 0)
        {
            throw ExceptionType.BOOK_NOT_FOUND.exception();
        }
    }

    void throwIfFullNameIncorrect(String fullName) {
        authorFilterDto = AuthorFilterDto.builder()
                .name(fullName)
                .build();

        if(authorService.findAllByFilter(authorFilterDto, Pageable.unpaged()).getSize() == 0)
        {
            throw ExceptionType.AUTHOR_NOT_FOUND.exception();
        }
    }
}
