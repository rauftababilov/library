package com.andersen.library.services.book_audit;

import com.andersen.library.services.book_audit.model.BookState;

public interface BookAuditValidatorService {

    void throwIfClientNotAllowed(Long clientId);

    void throwIfBookTakingNotAllowed(Long bookId);

    void throwIfBookIsReceived(BookState bookState);

    void throwIfBookChanged(Long oldBookId, Long newBookId);

}
