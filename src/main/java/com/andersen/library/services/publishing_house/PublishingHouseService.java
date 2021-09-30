package com.andersen.library.services.publishing_house;

import com.andersen.library.services.publishing_house.impl.PublishingHouse;

import java.util.List;

public interface PublishingHouseService{
    /**
     * Добавление издательства в БД
     *
     * @param publishingHouse publishingHouse
     * @return publishingHouse
     */
    PublishingHouse save(PublishingHouse publishingHouse);

    /**
     * Поиск всех издательств в БД
     *
     * @return list of publishingHouses
     */
    List<PublishingHouse> findAll();

    /**
     * Поиск издательства по id
     *
     * @param id id publishingHouse
     * @return publishingHouse
     */
    PublishingHouse findById(Long id);

    /**
     * Редактирование издательства в БД
     *
     * @param publishingHouse publishingHouse
     * @return updated publishingHouse
     */
    PublishingHouse update(PublishingHouse publishingHouse);

    /**
     * Удаление издательства по id из БД
     *
     * @param id of publishingHouse
     */
    void delete(Long id);
}
