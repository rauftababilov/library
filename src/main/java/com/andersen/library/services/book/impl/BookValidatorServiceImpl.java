package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book.BookValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
class BookValidatorServiceImpl implements BookValidatorService {

    @Override
    public void throwIfAuthorsIncorrect(List<Long> authorIds) {
        //TODO
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

}
