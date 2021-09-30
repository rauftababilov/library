package com.andersen.library.services.book;

import java.util.List;

public interface BookValidatorService {

    void throwIfAuthorsIncorrect(List<Long> authorIds);

    void throwIfPublishingHouseIncorrect(Long publishingHouse);

    void throwIfPublishYearIncorrect(Integer publishYear);

    void throwIfBookGiven(Long bookId);

    void throwIfBookDeleted(boolean deleted);

}
