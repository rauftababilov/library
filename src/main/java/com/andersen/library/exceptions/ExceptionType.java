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
    BOOK_DELETED("Book deleted", HttpStatus.CONFLICT),
    BOOK_PUBLISH_YEAR_INCORRECT("Publish year incorrect", HttpStatus.BAD_REQUEST),

    CLIENT_NOT_FOUND("Client was not found", HttpStatus.NOT_FOUND),
    CLIENT_DELETED("Client deleted", HttpStatus.CONFLICT),
    CLIENT_ALREADY_EXISTS("Client already exists", HttpStatus.CONFLICT),
    CLIENT_HAS_GIVEN_BOOK("Client has book on hands", HttpStatus.FORBIDDEN),

    AUTHOR_NOT_FOUND("Author was not found", HttpStatus.NOT_FOUND),
    AUTHOR_DELETED("Author deleted", HttpStatus.CONFLICT),

    PUBLISHING_HOUSE_NOT_FOUND("Publishing house was not found", HttpStatus.NOT_FOUND),

    AUDIT_RECORD_NOT_FOUND("Audit record was not found", HttpStatus.NOT_FOUND),
    BOOK_GIVEN("Current book is already given", HttpStatus.CONFLICT),
    BOOK_IN_AUDIT_CHANGING("Book in audit record cannot be changed", HttpStatus.CONFLICT),
    RECEIVED_BOOK_AUDIT_RECORD_CHANGING("Audit record about already received book cannot be changed",
            HttpStatus.CONFLICT),

    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    USER_DELETED("User deleted", HttpStatus.CONFLICT),
    USER_ALREADY_EXISTS("User already exists", HttpStatus.CONFLICT),
    ROOT_USER_DELETE("Root user cannot be deleted", HttpStatus.FORBIDDEN);

    @JsonValue
    private final String message;

    private final HttpStatus status;

    public ExtException exception() {
        return ExtException.of(this).build();
    }

}