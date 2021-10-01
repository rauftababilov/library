package com.andersen.library.services.book;

import com.andersen.library.services.book.model.BookDto;
import com.andersen.library.services.book.model.BookFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    /**
     * Find all books
     *
     * @param filterDto bookFilterDto
     * @param pageable pageable
     * @return list of bookDtos
     */
    Page<BookDto> getAll(BookFilterDto filterDto, Pageable pageable);

    /**
     * Find book by id
     *
     * @param id id book
     * @return bookDto
     */
    BookDto get(Long id);

    /**
     * Add book to DB
     *
     * @param dto bookDto
     * @return bookDto
     */
    BookDto create(BookDto dto);

    /**
     * Update book in DB
     *
     * @param id id book
     * @param dto bookDto
     * @return updated bookDto
     */
    BookDto update(Long id, BookDto dto);

    /**
     * Delete book by id from DB
     *
     * @param id id book
     */
    void softDelete(Long id);

}
