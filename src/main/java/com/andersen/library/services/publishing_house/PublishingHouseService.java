package com.andersen.library.services.publishing_house;

import com.andersen.library.services.publishing_house.model.PublishingHouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublishingHouseService {

    /**
     * Find all publishingHouses
     *
     * @param pageable pageable
     * @return list of publishingHouseDtos
     */
    Page<PublishingHouseDto> getAll(Pageable pageable);

    /**
     * Find publishingHouse by id
     *
     * @param id id publishingHouse
     * @return publishingHouseDto
     */
    PublishingHouseDto get(Long id);

    /**
     * Add publishingHouse to DB
     *
     * @param dto publishingHouseDto
     * @return publishingHouseDto
     */
    PublishingHouseDto create(PublishingHouseDto dto);

    /**
     * Update publishingHouse in DB
     *
     * @param id  id
     * @param dto publishingHouseDto
     * @return updated publishingHouseDto
     */
    PublishingHouseDto update(Long id, PublishingHouseDto dto);

    /**
     * Delete publishingHouse by id from DB
     *
     * @param id of publishingHouse
     */
    void softDelete(Long id);

}
