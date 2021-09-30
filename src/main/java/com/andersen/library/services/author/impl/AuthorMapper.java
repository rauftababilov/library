package com.andersen.library.services.author.impl;

import com.andersen.library.services.author.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface AuthorMapper {
    AuthorDto toDto(Author author);
}
