package com.andersen.library.services.book_audit.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book_audit.BookAuditService;
import com.andersen.library.services.book_audit.BookAuditValidatorService;
import com.andersen.library.services.book_audit.model.BookAuditDto;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BookAuditServiceImpl implements BookAuditService {

    private final BookAuditRepository repository;

    private final BookAuditMapper mapper;

    private final BookAuditValidatorService validatorService;

    @Override
    public Page<BookAuditDto> getAll(BookAuditFilterDto filterDto, Pageable pageable) {
        return repository
                .findAllWithFilter(filterDto.getBookId(), filterDto.getClientId(), filterDto.getBookState(), pageable)
                .map(mapper::toDto);
    }

    @Override
    public BookAuditDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);
    }

    @Override
    public BookAuditDto create(BookAuditDto dto) {
        BookAudit bookAudit = new BookAudit();

        validateAuditRecordOnCreate(dto);

        mapper.populateEntityDataByDto(bookAudit, dto);

        bookAudit = repository.save(bookAudit);

        return mapper.toDto(bookAudit);
    }

    @Override
    public BookAuditDto update(Long id, BookAuditDto dto) {
        BookAudit bookAudit = repository.findById(id)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);

        validateAuditRecordOnUpdate(dto, bookAudit);

        mapper.populateEntityDataByDto(bookAudit, dto);

        bookAudit = repository.save(bookAudit);

        return mapper.toDto(bookAudit);
    }

    @Override
    public void delete(Long id) {
        BookAudit bookAudit = repository.findById(id)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);

        repository.delete(bookAudit);
    }

    private void validateAuditRecordOnCreate(BookAuditDto dto) {
        validatorService.throwIfClientNotAllowed(dto.getClientId());
        validatorService.throwIfBookTakingNotAllowed(dto.getBookId());
    }

    private void validateAuditRecordOnUpdate(BookAuditDto dto, BookAudit bookAudit) {
        validatorService.throwIfBookIsReceived(bookAudit.getBookState());
        validatorService.throwIfClientNotAllowed(dto.getClientId());
        validatorService.throwIfBookChanged(bookAudit.getBookId(), dto.getBookId());
    }

}
