package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.publishing_house.PublishingHouseValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PublishingHouseValidatorServiceImpl implements PublishingHouseValidatorService {

    private final PublishingHouseRepository repository;

    @Override
    public void throwIfPublishingHouseAlreadyExists(String title) {
        if (repository.existsByTitleAndDeletedIsFalse(title)) {
            throw ExceptionType.PUBLISHING_HOUSE_ALREADY_EXISTS.exception();
        }
    }

    @Override
    public void throwIfPublishingHouseDeleted(boolean deleted) {
        if (deleted) {
            throw ExceptionType.PUBLISHING_HOUSE_DELETED.exception();
        }
    }

    @Override
    public void throwIfNewTitleNotAllowed(String oldTitle, String newTitle) {
        if (!oldTitle.equals(newTitle)) {
            throwIfPublishingHouseAlreadyExists(newTitle);
        }
    }

}
