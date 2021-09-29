package com.andersen.library.jpa.repository;

import com.andersen.library.jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
