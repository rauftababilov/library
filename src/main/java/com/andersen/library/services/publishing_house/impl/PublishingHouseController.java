package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.services.publishing_house.PublishingHouseDto;
import com.andersen.library.services.publishing_house.PublishingHouseService;
import com.andersen.library.services.publishing_house.PublishingHouseUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @GetMapping(PublishingHouseUrl.GET)
    public PublishingHouseDto findById(@PathVariable Long publishingHouseId) {
        return publishingHouseService.findById(publishingHouseId);
    }

    @GetMapping(PublishingHouseUrl.FIND)
    public Page<PublishingHouseDto> findAll(Pageable pageable) {
        return publishingHouseService.findAll(pageable);
    }

    @GetMapping(PublishingHouseUrl.FIND)
    public Page<PublishingHouseDto> findAllByFilter(String title, Pageable pageable) {
        return publishingHouseService.findAllByTitle(title, pageable);
    }

    @PostMapping(PublishingHouseUrl.CREATE)
    public PublishingHouseDto save(@Valid @RequestBody PublishingHouseDto publishingHouseDto) {
        return publishingHouseService.save(publishingHouseDto);
    }

    @PatchMapping(PublishingHouseUrl.UPDATE)
    public PublishingHouseDto update(@PathVariable Long publishingHouseId, @Valid @RequestBody PublishingHouseDto publishingHouseDto) {
        return publishingHouseService.update(publishingHouseId, publishingHouseDto);
    }

    @DeleteMapping(PublishingHouseUrl.DELETE)
    public void delete(@PathVariable Long publishingHouseId) {
        publishingHouseService.delete(publishingHouseId);
    }
}
