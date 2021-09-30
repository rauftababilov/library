package com.andersen.library.services.record_keeping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordKeepingService {

    Page<RecordKeepingDto> getAll(RecordKeepingFilterDto filterDto, Pageable pageable);

    RecordKeepingDto get(Long id);

    RecordKeepingDto create(RecordKeepingDto dto);

    RecordKeepingDto fullUpdate(Long id, RecordKeepingDto dto);

    void delete(Long id);

}
