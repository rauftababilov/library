package com.andersen.library.services.client.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.record_keeping.BookState;
import com.andersen.library.services.record_keeping.RecordKeepingFilterDto;
import com.andersen.library.services.record_keeping.RecordKeepingService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ClientValidatorService {

    private final ClientRepository repository;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private RecordKeepingService recordKeepingService;

    public void throwIfClientAlreadyExists(String clientName) {
        if (repository.existsByFullName(clientName)) {
            throw ExceptionType.CLIENT_ALREADY_EXISTS.exception();
        }
    }

    public void throwIfClientNameChangeNotAllowed(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            throwIfClientAlreadyExists(newName);
        }
    }

    public void throwIfClientHasGivenBook(Long clientId) {
        RecordKeepingFilterDto filterDto = RecordKeepingFilterDto.builder()
                .clientId(clientId)
                .bookState(BookState.GIVEN)
                .build();

        if (recordKeepingService.getAll(filterDto, Pageable.unpaged()).getSize() != 0) {
            throw ExceptionType.CLIENT_HAS_GIVEN_BOOK.exception();
        }
    }

}
