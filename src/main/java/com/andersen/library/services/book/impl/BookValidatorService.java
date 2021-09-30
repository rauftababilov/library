package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
class BookValidatorService {

    void throwIfAuthorsIncorrect(List<Long> authorIds) {
        //TODO
    }

    void throwIfPublishingHouseIncorrect(Long publishingHouse) {
        //TODO
    }

    void throwIfPublishYearIncorrect(Integer publishYear) {
        if (Year.now().getValue() < publishYear) {
            throw ExceptionType.BOOK_PUBLISH_YEAR_INCORRECT.exception();
        }
    }

}
