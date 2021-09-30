package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.publishing_house.PublishingHouseDto;
import com.andersen.library.services.publishing_house.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;
    private final PublishingHouseMapper publishingHouseMapper;
    private final PublishingHouseValidatorService publishingHouseValidatorService;

    @Override
    public PublishingHouseDto save(PublishingHouseDto publishingHouseDto) {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouseValidatorService.throwIfTitleIncorrect(publishingHouseDto.getTitle());

        publishingHouseRepository.save(publishingHouse);

        return publishingHouseMapper.toDto(publishingHouse);
    }

    @Override
    public Page<PublishingHouseDto> findAll(Pageable pageable) {
        return publishingHouseRepository.findAll(pageable)
                .map(publishingHouseMapper::toDto);
    }

    @Override
    public Page<PublishingHouseDto> findAllByTitle(String title, Pageable pageable) {
        return publishingHouseRepository.findAllByTitle(title, pageable)
                .map(publishingHouseMapper::toDto);
    }

    @Override
    public PublishingHouseDto findById(Long id) {
        return publishingHouseRepository.findById(id)
                .map(publishingHouseMapper::toDto)
                .orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);
    }

    @Override
    public PublishingHouseDto update(Long id, PublishingHouseDto publishingHouseDto) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(id).orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);

        publishingHouseValidatorService.throwIfTitleIncorrect(publishingHouseDto.getTitle());

        publishingHouse.setTitle(publishingHouseDto.getTitle());

        publishingHouseRepository.save(publishingHouse);

        return publishingHouseMapper.toDto(publishingHouse);
    }

    @Override
    public void delete(Long id) {
        PublishingHouse publishingHouse = publishingHouseRepository.findById(id).orElseThrow(ExceptionType.PUBLISHING_HOUSE_NOT_FOUND::exception);

        publishingHouseRepository.delete(publishingHouse);
    }
}





