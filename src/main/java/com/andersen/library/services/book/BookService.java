package com.andersen.library.services.book;

import com.andersen.library.services.book.dto.BookDto;
import com.andersen.library.services.book.dto.BookFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDto> getAll(BookFilterDto filterDto, Pageable pageable);

    BookDto get(Long id);

    BookDto create(BookDto dto);

    BookDto fullUpdate(Long id, BookDto dto);

    void delete(Long id);

}
