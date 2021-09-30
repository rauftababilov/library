package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book.BookService;
import com.andersen.library.services.client.ClientService;
import com.andersen.library.services.record_keeping.BookState;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RecordKeepingValidatorService {

    private final RecordKeepingRepository repository;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private BookService bookService;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private ClientService clientService;

    public void throwIfClientNotAllowed(Long clientId) {
        clientService.get(clientId);
    }

    public void throwIfBookTakingNotAllowed(Long bookId) {
        bookService.get(bookId);

        if (repository.existsByBookStateAndBookId(BookState.GIVEN, bookId)) {
            throw ExceptionType.BOOK_GIVEN.exception();
        }
    }

    public void throwIfBookIsRecieved(BookState bookState) {
        if (BookState.RECIEVED.equals(bookState)) {
            throw ExceptionType.RECIEVED_BOOK_AUDIT_RECORD_CHANGING.exception();
        }
    }

    public void throwIfBookChanged(Long oldBookId, Long newBookId) {
        if (!oldBookId.equals(newBookId)) {
            throw ExceptionType.BOOK_IN_AUDIT_CHANGING.exception();
        }
    }

}
