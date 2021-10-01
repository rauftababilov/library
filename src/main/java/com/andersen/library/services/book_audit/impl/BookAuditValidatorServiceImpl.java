package com.andersen.library.services.book_audit.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book.BookService;
import com.andersen.library.services.book_audit.BookAuditValidatorService;
import com.andersen.library.services.book_audit.model.BookState;
import com.andersen.library.services.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BookAuditValidatorServiceImpl implements BookAuditValidatorService {

    private final BookAuditRepository repository;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private BookService bookService;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private ClientService clientService;

    @Override
    public void throwIfClientNotAllowed(Long clientId) {
        clientService.get(clientId, false);
    }

    @Override
    public void throwIfBookTakingNotAllowed(Long bookId) {
        bookService.get(bookId, false);

        if (repository.existsByBookStateAndBookId(BookState.GIVEN, bookId)) {
            throw ExceptionType.BOOK_GIVEN.exception();
        }
    }

    @Override
    public void throwIfBookIsReceived(BookState bookState) {
        if (BookState.RECEIVED.equals(bookState)) {
            throw ExceptionType.RECEIVED_BOOK_AUDIT_RECORD_CHANGING.exception();
        }
    }

    @Override
    public void throwIfBookChanged(Long oldBookId, Long newBookId) {
        if (!oldBookId.equals(newBookId)) {
            throw ExceptionType.BOOK_IN_AUDIT_CHANGING.exception();
        }
    }

}
