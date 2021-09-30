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

    BOOK_NOT_FOUND("List was not found", HttpStatus.NOT_FOUND),
    BOOK_PUBLISH_YEAR_INCORRECT("Publish year incorrect", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND);

    @JsonValue
    private final String message;

    private final HttpStatus status;

    public ExtException exception() {
        return ExtException.of(this).build();
    }

}