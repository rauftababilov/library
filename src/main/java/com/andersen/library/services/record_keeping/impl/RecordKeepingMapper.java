package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.services.record_keeping.RecordKeepingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordKeepingMapper {

    RecordKeepingDto toDto(RecordKeeping entity);

}
