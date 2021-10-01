package com.andersen.library.services.book_audit;

import com.andersen.library.services.book_audit.model.BookAuditDto;
import com.andersen.library.services.book_audit.model.BookAuditFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookAuditService {

    /**
     * Find all bookAudits
     *
     * @param filterDto bookAuditFilterDto
     * @param pageable  pageable
     * @return list of bookAuditDtos
     */
    Page<BookAuditDto> getAll(BookAuditFilterDto filterDto, Pageable pageable);

    /**
     * Find bookAudit by id
     *
     * @param id id bookAudit
     * @return bookAuditDto
     */
    BookAuditDto get(Long id);

    /**
     * Add bookAudit to DB
     *
     * @param dto bookAuditDto
     * @return bookAuditDto
     */
    BookAuditDto create(BookAuditDto dto);

    /**
     * Update bookAudit in DB
     *
     * @param id  id bookAudit
     * @param dto bookAuditDto
     * @return updated bookAuditDto
     */
    BookAuditDto update(Long id, BookAuditDto dto);

    /**
     * Delete bookAudit by id from DB
     *
     * @param id id bookAudit
     */
    void delete(Long id);

}
