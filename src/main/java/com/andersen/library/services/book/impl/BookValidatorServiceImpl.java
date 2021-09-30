package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
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
class BookValidatorService {

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private RecordKeepingService recordKeepingService;

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

    public void throwIfBookGiven(Long bookId) {
        RecordKeepingFilterDto filterDto = RecordKeepingFilterDto.builder()
                .bookState(BookState.GIVEN)
                .bookId(bookId)
                .build();

        if (recordKeepingService.getAll(filterDto, Pageable.unpaged()).getSize() != 0) {
            throw ExceptionType.BOOK_GIVEN.exception();
        }
    }

}
