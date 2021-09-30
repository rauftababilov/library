package com.andersen.library.services.book.impl;

import com.andersen.library.services.book.BookDto;
import com.andersen.library.services.book.BookFilterDto;
import com.andersen.library.services.book.BookService;
import com.andersen.library.services.book.BookUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class BookController {

    private final BookService bookService;

    @GetMapping(BookUrl.GET)
    public BookDto get(@PathVariable Long bookId) {
        return bookService.get(bookId);
    }

    @GetMapping(BookUrl.FIND)
    public Page<BookDto> getAll(BookFilterDto filterDto, Pageable pageable) {
        return bookService.getAll(filterDto, pageable);
    }

    @PostMapping(BookUrl.CREATE)
    public BookDto create(@Valid @RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @PatchMapping(BookUrl.UPDATE)
    public BookDto update(@PathVariable Long bookId, @Valid @RequestBody BookDto dto) {
        return bookService.fullUpdate(bookId, dto);
    }

    @DeleteMapping(BookUrl.DELETE)
    public void delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
    }

}
