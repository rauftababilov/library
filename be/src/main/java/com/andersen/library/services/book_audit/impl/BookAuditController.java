package com.andersen.library.services.book_audit.impl;

import com.andersen.library.services.book_audit.model.BookAuditDto;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import com.andersen.library.services.book_audit.BookAuditService;
import com.andersen.library.services.book_audit.BookAuditUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class BookAuditController {

    private final BookAuditService bookAuditService;

    @GetMapping(BookAuditUrl.GET)
    public BookAuditDto get(@PathVariable Long auditId) {
        return bookAuditService.get(auditId);
    }

    @GetMapping(BookAuditUrl.FIND)
    public Page<BookAuditDto> getAll(BookAuditFilterDto filterDto, Pageable pageable) {
        return bookAuditService.getAll(filterDto, pageable);
    }

    @PostMapping(BookAuditUrl.CREATE)
    public BookAuditDto create(@Valid @RequestBody BookAuditDto dto) {
        return bookAuditService.create(dto);
    }

    @PutMapping(BookAuditUrl.UPDATE)
    public BookAuditDto update(@PathVariable Long auditId, @Valid @RequestBody BookAuditDto dto) {
        return bookAuditService.update(auditId, dto);
    }

    @DeleteMapping(BookAuditUrl.DELETE)
    public void delete(@PathVariable Long auditId) {
        bookAuditService.delete(auditId);
    }

}
