package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.services.record_keeping.RecordKeepingDto;
import com.andersen.library.services.record_keeping.RecordKeepingFilterDto;
import com.andersen.library.services.record_keeping.RecordKeepingService;
import com.andersen.library.services.record_keeping.RecordKeepingUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RecordKeepingController {

    private final RecordKeepingService recordKeepingService;

    @GetMapping(RecordKeepingUrl.GET)
    public RecordKeepingDto get(@PathVariable Long auditId) {
        return recordKeepingService.get(auditId);
    }

    @GetMapping(RecordKeepingUrl.FIND)
    public Page<RecordKeepingDto> getAll(RecordKeepingFilterDto filterDto, Pageable pageable) {
        return recordKeepingService.getAll(filterDto, pageable);
    }

    @PostMapping(RecordKeepingUrl.CREATE)
    public RecordKeepingDto create(@Valid @RequestBody RecordKeepingDto dto) {
        return recordKeepingService.create(dto);
    }

    @PatchMapping(RecordKeepingUrl.UPDATE)
    public RecordKeepingDto update(@PathVariable Long auditId, @Valid @RequestBody RecordKeepingDto dto) {
        return recordKeepingService.fullUpdate(auditId, dto);
    }

    @DeleteMapping(RecordKeepingUrl.DELETE)
    public void delete(@PathVariable Long auditId) {
        recordKeepingService.delete(auditId);
    }

}
