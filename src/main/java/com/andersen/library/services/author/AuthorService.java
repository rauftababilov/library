package com.andersen.library.services.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService{
    /**
     * Add author to DB
     *
     * @param authorDto author
     * @return authorDto
     */
    AuthorDto save(AuthorDto authorDto);

    /**
     * Find all authors
     *
     * @param authorFilterDto authorFilterDto
     * @param pageable pageable
     * @return list of authorDtos
     */
    Page<AuthorDto> findAllByFilter(AuthorFilterDto authorFilterDto, Pageable pageable);

    /**
     * Find author by id
     *
     * @param id id author
     * @return authorDto
     */
    AuthorDto findById(Long id);

    /**
     * Update author in DB
     *
     * @param id id
     * @param authorDto author
     * @return updated authorDto
     */
    AuthorDto update(Long id, AuthorDto authorDto);

    /**
     * Delete author by id from DB
     *
     * @param id of author
     */
    void delete(Long id);
}


