package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.services.record_keeping.BookState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface RecordKeepingRepository extends JpaRepository<RecordKeeping, Long> {

    boolean existsByBookStateAndBookId(BookState given, Long bookId);

    @Query("select r from RecordKeeping r" +
            " where (:bookId is null or r.bookId = :bookId)" +
            " and (:clientId is null or r.clientId = :clientId)" +
            " and (:bookState is null or r.bookState = :bookState)" +
            " order by r.updatedAt desc")
    Page<RecordKeeping> findAllWithFilter(Long bookId, Long clientId, BookState bookState, Pageable pageable);

}
