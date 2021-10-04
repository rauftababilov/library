package com.andersen.library.services.client.impl;

import com.andersen.library.services.client.model.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ClientMapper {

    ClientDto toDto(Client entity);

    default void populateClientEntityByDto(Client client, ClientDto dto) {
        client.setBirthday(dto.getBirthday());
        client.setFullName(dto.getFullName());
    }

}
