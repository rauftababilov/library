package com.andersen.library.services.client.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book_audit.BookAuditService;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import com.andersen.library.services.book_audit.model.BookState;
import com.andersen.library.services.client.ClientValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ClientValidatorServiceImpl implements ClientValidatorService {

    private final ClientRepository repository;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private BookAuditService bookAuditService;

    @Override
    public void throwIfClientAlreadyExists(String clientName) {
        if (repository.existsByFullName(clientName)) {
            throw ExceptionType.CLIENT_ALREADY_EXISTS.exception();
        }
    }

    @Override
    public void throwIfClientNameChangeNotAllowed(String oldName, String newName) {
        if (!oldName.equals(newName)) {
            throwIfClientAlreadyExists(newName);
        }
    }

    @Override
    public void throwIfClientHasGivenBook(Long clientId) {
        BookAuditFilterDto filterDto = BookAuditFilterDto.builder()
                .clientId(clientId)
                .bookState(BookState.GIVEN)
                .build();

        if (bookAuditService.getAll(filterDto, Pageable.unpaged()).getSize() != 0) {
            throw ExceptionType.CLIENT_HAS_GIVEN_BOOK.exception();
        }
    }

    @Override
    public void throwIfClientDeleted(boolean deleted) {
        if (deleted) {
            throw ExceptionType.CLIENT_DELETED.exception();
        }
    }

}
