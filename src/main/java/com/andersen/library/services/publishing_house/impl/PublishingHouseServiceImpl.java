package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.services.publishing_house.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {
    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Override
    public PublishingHouse save(PublishingHouse publishingHouse) {
        return null;
    }

    @Override
    public List<PublishingHouse> findAll() {
        return publishingHouseRepository.findAll();
    }

    @Override
    public PublishingHouse findById(Long id) {
        return null;
    }

    @Override
    public PublishingHouse update(PublishingHouse publishingHouse) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
