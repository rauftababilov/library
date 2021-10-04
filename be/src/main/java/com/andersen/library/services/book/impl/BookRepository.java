package com.andersen.library.services.book.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Set;

interface BookRepository extends JpaRepository<Book, Long> {

    @QueryHints(value = {
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")
    })
    @Query("select distinct b from Book b inner join b.authorIds a" +
            " where b.deleted = false " +
            " and (:publishingHouseId is null or b.publishingHouseId in (:publishingHouseId))" +
            " and (:authorId is null or a in (:authorId))" +
            " order by b.createdAt desc")
    Page<Book> findAllByFilter(Set<Long> authorId, Set<Long> publishingHouseId, Pageable pageable);

}
