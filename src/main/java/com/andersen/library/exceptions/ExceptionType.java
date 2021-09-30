package com.andersen.library.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    UNKNOWN("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("Insufficient privileges", HttpStatus.FORBIDDEN),

    BOOK_NOT_FOUND("Book was not found", HttpStatus.NOT_FOUND),
    BOOK_PUBLISH_YEAR_INCORRECT("Publish year incorrect", HttpStatus.BAD_REQUEST),

    CLIENT_NOT_FOUND("Client was not found", HttpStatus.NOT_FOUND),
    CLIENT_ALREADY_EXISTS("Client already exists", HttpStatus.CONFLICT),
    CLIENT_HAS_GIVEN_BOOK("Client has book on hands", HttpStatus.FORBIDDEN),

    AUDIT_RECORD_NOT_FOUND("Audit record was not found", HttpStatus.NOT_FOUND),
    BOOK_GIVEN("Current book is already given", HttpStatus.CONFLICT),
    BOOK_IN_AUDIT_CHANGING("Book in audit record cannot be changed", HttpStatus.CONFLICT),
    RECIEVED_BOOK_AUDIT_RECORD_CHANGING("Audit record about already recieved book a=cannot be changed",
            HttpStatus.CONFLICT),

    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND);

    @JsonValue
    private final String message;

    private final HttpStatus status;

    public ExtException exception() {
        return ExtException.of(this).build();
    }

}