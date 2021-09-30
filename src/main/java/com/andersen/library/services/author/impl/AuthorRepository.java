package com.andersen.library.services.author.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    @Query("select a from Author a" +
            " where (a.fullName is null or a.fullName like (:name)%)" +
            " order by a.fullName asc")
    Page<Author> findAllByFilter(String name, Pageable pageable);
}
