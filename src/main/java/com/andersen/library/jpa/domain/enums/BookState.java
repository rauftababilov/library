package com.andersen.library.jpa.domain.enums;

/**
 * State of book location
 */
public enum BookState {
    RECIEVED, // возвращена/поступила в библиотеку
    GIVEN; // выдана клиенту
}