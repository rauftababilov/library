package com.andersen.library.services.publishing_house.impl;

import com.andersen.library.services.publishing_house.PublishingHouseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface PublishingHouseMapper {
    PublishingHouseDto toDto(PublishingHouse publishingHouse);
}
