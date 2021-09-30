package com.andersen.library.services.client.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c" +
            " where c.deleted = false" +
            " and (:name is null or c.fullName like %:name%)" +
            " and (:yearOfBirth is null or year(c.birthday) = :yearOfBirth)" +
            " order by c.updatedAt desc")
    Page<Client> findAllByFilter(String name, Integer yearOfBirth, Pageable pageable);

    boolean existsByFullNameAndDeletedIsFalse(String fullName);

}
