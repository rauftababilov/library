package com.andersen.library.services.book;

import com.andersen.library.services.book.model.BookDto;
import com.andersen.library.services.book.model.BookFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDto> getAll(BookFilterDto filterDto, Pageable pageable);

    BookDto get(Long id);

    BookDto create(BookDto dto);

    BookDto update(Long id, BookDto dto);

    void softDelete(Long id);

}
