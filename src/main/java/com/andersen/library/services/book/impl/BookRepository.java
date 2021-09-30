package com.andersen.library.services.book.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where (:authorId is null or b.authorIds in (:authorId))" +
            " and (:publishingHouseId is null or b.publishingHouseId in (:publishingHouseId))" +
            " order by b.createdAt desc")
    Page<Book> findAllWithFilter(Set<Long> authorId, Set<Long> publishingHouseId, Pageable pageable);

}
