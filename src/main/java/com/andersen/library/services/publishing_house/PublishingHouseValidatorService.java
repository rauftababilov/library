package com.andersen.library.services.publishing_house;

public interface PublishingHouseValidatorService {

    void throwIfPublishingHouseAlreadyExists(String title);

    void throwIfPublishingHouseDeleted(boolean deleted);

    void throwIfNewTitleNotAllowed(String oldTitle, String newTitle);

}
