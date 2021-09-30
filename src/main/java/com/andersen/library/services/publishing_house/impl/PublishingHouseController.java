package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.services.publishing_house.PublishingHouseService;
import com.andersen.library.services.publishing_house.PublishingHouseUrl;
import com.andersen.library.services.publishing_house.model.PublishingHouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    @GetMapping(PublishingHouseUrl.FIND)
    public Page<PublishingHouseDto> getAll(Pageable pageable) {
        return publishingHouseService.getAll(pageable);
    }

    @GetMapping(PublishingHouseUrl.GET)
    public PublishingHouseDto get(@PathVariable Long publishingHouseId) {
        return publishingHouseService.get(publishingHouseId);
    }

    @PostMapping(PublishingHouseUrl.CREATE)
    public PublishingHouseDto create(@Valid @RequestBody PublishingHouseDto publishingHouseDto) {
        return publishingHouseService.create(publishingHouseDto);
    }

    @PutMapping(PublishingHouseUrl.UPDATE)
    public PublishingHouseDto update(@PathVariable Long publishingHouseId,
                                     @Valid @RequestBody PublishingHouseDto publishingHouseDto) {
        return publishingHouseService.update(publishingHouseId, publishingHouseDto);
    }

    @DeleteMapping(PublishingHouseUrl.DELETE)
    public void delete(@PathVariable Long publishingHouseId) {
        publishingHouseService.softDelete(publishingHouseId);
    }

}
