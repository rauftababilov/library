package com.andersen.library.services.book_audit;

import com.andersen.library.services.book_audit.model.BookAuditDto;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookAuditService {

    Page<BookAuditDto> getAll(BookAuditFilterDto filterDto, Pageable pageable);

    BookAuditDto get(Long id);

    BookAuditDto create(BookAuditDto dto);

    BookAuditDto update(Long id, BookAuditDto dto);

    void delete(Long id);

}
