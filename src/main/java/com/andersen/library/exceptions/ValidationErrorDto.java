package com.andersen.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorDto {

    private String message;

    private Object value;

    private String field;

}
