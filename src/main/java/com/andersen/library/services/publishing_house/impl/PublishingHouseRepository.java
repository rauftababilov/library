package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.services.publishing_house.impl.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long>, JpaSpecificationExecutor<PublishingHouse> {
}
