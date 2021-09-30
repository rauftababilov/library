package com.andersen.library.jpa.domain.enums;

import lombok.AllArgsConstructor;

/**
 * Статус нахождения книги
 */
@AllArgsConstructor
public enum BookState {

    RECIEVED ("RECIEVED"), // возвращена/поступила в библиотеку
    GIVEN ("GIVEN"); // выдана клиенту

    private String name;

}