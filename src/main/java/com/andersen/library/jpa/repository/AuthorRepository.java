package com.andersen.library.jpa.repository;

import com.andersen.library.jpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
