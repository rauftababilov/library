package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.publishing_house.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishingHouseValidatorService {

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private PublishingHouseService publishingHouseService;

    void throwIfTitleIncorrect(String title) {
        if(publishingHouseService.findAllByTitle(title, Pageable.unpaged()).getSize() == 0)
        {
            throw ExceptionType.PUBLISHING_HOUSE_NOT_FOUND.exception();
        }
    }
}
