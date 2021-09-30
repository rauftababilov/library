package com.andersen.library.services.client.impl;

import com.andersen.library.services.client.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ClientMapper {

    ClientDto toDto(Client entity);

}
