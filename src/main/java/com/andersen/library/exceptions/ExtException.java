package com.andersen.library.exceptions;

import lombok.*;

import java.util.Map;

@Data
@Builder(builderMethodName = "innerBuilder", access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = false)
public class ExtException extends RuntimeException {

    private final ExceptionType type;

    private String message;

    @Singular
    private Map<String, String> details;

    public static ExtExceptionBuilder of(ExceptionType type) {
        return innerBuilder().type(type);
    }

    public String getMessage() {
        if (message == null) {
            return type.getMessage();
        }
        return message;
    }

}
