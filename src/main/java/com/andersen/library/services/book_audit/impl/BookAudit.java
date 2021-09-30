package com.andersen.library.services.book_audit.impl;

import com.andersen.library.jpa.FullAuditedEntity;
import com.andersen.library.services.book_audit.model.BookState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Entity
@Table(name = "book_audit")
@Data
@EqualsAndHashCode(callSuper = true)
class BookAudit extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_audit_generator")
    @SequenceGenerator(name = "book_audit_generator", sequenceName = "seq_book_audit", allocationSize = 20)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "book_state")
    @Enumerated(value = EnumType.STRING)
    private BookState bookState;

}
