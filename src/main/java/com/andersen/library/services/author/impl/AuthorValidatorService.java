package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorValidatorService {
    void throwIfBooksIncorrect(List<Long> bookIds) {
        //TODO
    }

    void throwIfFullNameIncorrect(String fullName) {
        //TODO
    }
}
