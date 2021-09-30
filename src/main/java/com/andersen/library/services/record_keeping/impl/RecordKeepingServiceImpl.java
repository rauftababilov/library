package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.record_keeping.RecordKeepingDto;
import com.andersen.library.services.record_keeping.RecordKeepingFilterDto;
import com.andersen.library.services.record_keeping.RecordKeepingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RecordKeepingServiceImpl implements RecordKeepingService {

    private final RecordKeepingRepository repository;

    private final RecordKeepingMapper mapper;

    private final RecordKeepingValidatorService validatorService;

    @Override
    public Page<RecordKeepingDto> getAll(RecordKeepingFilterDto filterDto, Pageable pageable) {
        return repository
                .findAllWithFilter(filterDto.getBookId(), filterDto.getClientId(), filterDto.getBookState(), pageable)
                .map(mapper::toDto);
    }

    @Override
    public RecordKeepingDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);
    }

    @Override
    public RecordKeepingDto create(RecordKeepingDto dto) {
        RecordKeeping recordKeeping = new RecordKeeping();

        validatorService.throwIfClientNotAllowed(dto.getClientId());
        validatorService.throwIfBookTakingNotAllowed(dto.getBookId());

        recordKeeping.setBookId(dto.getBookId());
        recordKeeping.setBookState(dto.getBookState());
        recordKeeping.setClientId(dto.getClientId());

        recordKeeping = repository.save(recordKeeping);

        return mapper.toDto(recordKeeping);
    }

    @Override
    public RecordKeepingDto fullUpdate(Long id, RecordKeepingDto dto) {
        RecordKeeping recordKeeping = repository.findById(id)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);

        validatorService.throwIfBookIsRecieved(recordKeeping.getBookState());
        validatorService.throwIfClientNotAllowed(dto.getClientId());
        validatorService.throwIfBookChanged(recordKeeping.getBookId(), dto.getBookId());

        recordKeeping.setBookId(dto.getBookId());
        recordKeeping.setBookState(dto.getBookState());
        recordKeeping.setClientId(dto.getClientId());

        recordKeeping = repository.save(recordKeeping);

        return mapper.toDto(recordKeeping);
    }

    @Override
    public void delete(Long id) {
        RecordKeeping recordKeeping = repository.findById(id)
                .orElseThrow(ExceptionType.AUDIT_RECORD_NOT_FOUND::exception);

        repository.delete(recordKeeping);
    }

}
