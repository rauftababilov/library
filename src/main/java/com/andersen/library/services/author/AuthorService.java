package com.andersen.library.services.author;

import com.andersen.library.services.author.impl.Author;

import java.util.List;

public interface AuthorService{
    /**
     * Add author to DB
     *
     * @param author author
     * @return author
     */
    Author save(Author author);

    /**
     * Find all authors
     *
     * @return list of authors
     */
    List<Author> findAll();

    /**
     * Find author by id
     *
     * @param id id author
     * @return author
     */
    Author findById(Long id);

    /**
     * Поиск всех авторов с указанной фамилией в БД
     *
     * @param firstName firstName
     * @return list of authors
     */
    List<Author> findAllByFirstName(String firstName);

    /**
     * Редактирование автора в БД
     *
     * @param author author
     * @return updated author
     */
    Author update(Author author);

    /**
     * Удаление автора по id из БД
     *
     * @param id of author
     */
    void delete(Long id);
}
