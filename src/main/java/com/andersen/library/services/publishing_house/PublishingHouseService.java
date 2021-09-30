package com.andersen.library.services.publishing_house;

import com.andersen.library.services.author.AuthorDto;
import com.andersen.library.services.author.AuthorFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublishingHouseService {
    /**
     * Add publishingHouse to DB
     *
     * @param publishingHouseDto publishingHouseDto
     * @return publishingHouseDto
     */
    PublishingHouseDto save(PublishingHouseDto publishingHouseDto);

    /**
     * Find all publishingHouses
     *
     * @param pageable pageable
     * @return list of publishingHouseDtos
     */
    Page<PublishingHouseDto> findAll(Pageable pageable);

    /**
     * Find all publishingHouses by title
     *
     * @param title title
     * @param pageable pageable
     * @return list of publishingHouseDtos
     */
    Page<PublishingHouseDto> findAllByTitle(String title, Pageable pageable);

    /**
     * Find publishingHouse by id
     *
     * @param id id publishingHouse
     * @return publishingHouseDto
     */
    PublishingHouseDto findById(Long id);

    /**
     * Update publishingHouse in DB
     *
     * @param id id
     * @param publishingHouseDto publishingHouseDto
     * @return updated publishingHouseDto
     */
    PublishingHouseDto update(Long id, PublishingHouseDto publishingHouseDto);

    /**
     * Delete publishingHouse by id from DB
     *
     * @param id of publishingHouse
     */
    void delete(Long id);
}


