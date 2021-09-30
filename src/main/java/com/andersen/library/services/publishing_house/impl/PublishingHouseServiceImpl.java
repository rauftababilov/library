package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.publishing_house.PublishingHouseService;
import com.andersen.library.services.publishing_house.PublishingHouseValidatorService;
import com.andersen.library.services.publishing_house.model.PublishingHouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository repository;

    private final PublishingHouseMapper mapper;

    private final PublishingHouseValidatorService validatorService;

    @Override
    public PublishingHouseDto create(PublishingHouseDto dto) {
        PublishingHouse publishingHouse = new PublishingHouse();

        validatorService.throwIfPublishingHouseAlreadyExists(dto.getTitle());

        publishingHouse.setTitle(dto.getTitle());

        publishingHouse = repository.save(publishingHouse);

        return mapper.toDto(publishingHouse);
    }

    @Override
    public Page<PublishingHouseDto> getAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable).map(mapper::toDto);
    }

    @Override
    public PublishingHouseDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);
    }

    @Override
    public PublishingHouseDto update(Long id, PublishingHouseDto dto) {
        PublishingHouse publishingHouse = repository.findById(id).orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);

        validatePublishingHouseOnUpdate(dto, publishingHouse);

        publishingHouse.setTitle(dto.getTitle());

        publishingHouse = repository.save(publishingHouse);

        return mapper.toDto(publishingHouse);
    }

    @Override
    public void softDelete(Long id) {
        PublishingHouse publishingHouse = repository.findById(id).orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);

        validatorService.throwIfPublishingHouseDeleted(publishingHouse.isDeleted());

        publishingHouse.setDeleted(true);

        repository.save(publishingHouse);
    }

    private void validatePublishingHouseOnUpdate(PublishingHouseDto dto, PublishingHouse publishingHouse) {
        validatorService.throwIfNewTitleNotAllowed(publishingHouse.getTitle(), dto.getTitle());
        validatorService.throwIfPublishingHouseDeleted(publishingHouse.isDeleted());
    }

}
