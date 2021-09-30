package com.andersen.library.services.author;

import com.andersen.library.services.author.model.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    /**
     * Find all authors
     *
     * @param pageable pageable
     * @return list of authorDtos
     */
    Page<AuthorDto> getAll(Pageable pageable);

    /**
     * Find author by id
     *
     * @param id id author
     * @return authorDto
     */
    AuthorDto get(Long id);

    /**
     * Add author to DB
     *
     * @param authorDto author
     * @return authorDto
     */
    AuthorDto create(AuthorDto authorDto);

    /**
     * Update author in DB
     *
     * @param id        id
     * @param authorDto author
     * @return updated authorDto
     */
    AuthorDto update(Long id, AuthorDto authorDto);

    /**
     * Delete author by id from DB
     *
     * @param id of author
     */
    void softDelete(Long id);

}
