package com.andersen.library.services.author.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findAllByDeletedIsFalse(Pageable pageable);

    List<Author> findAllByIdIn(Collection<Long> id);

}
