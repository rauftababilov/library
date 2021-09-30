package com.andersen.library.services.client.impl;

import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByFullName(String fullName);

}
