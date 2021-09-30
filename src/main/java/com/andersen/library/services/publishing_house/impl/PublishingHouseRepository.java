package com.andersen.library.services.publishing_house.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {

    Page<PublishingHouse> findAllByDeletedIsFalse(Pageable pageable);

    boolean existsByTitleAndDeletedIsFalse(String title);

}
