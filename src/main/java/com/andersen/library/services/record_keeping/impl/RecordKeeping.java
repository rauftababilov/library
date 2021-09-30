package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import com.andersen.library.services.record_keeping.BookState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Entity
@Table(name = "record_keeping")
@Data
@EqualsAndHashCode(callSuper = true)
class RecordKeeping extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_keeping_generator")
    @SequenceGenerator(name = "record_keeping_generator", sequenceName = "seq_record_keeping", allocationSize = 20)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "book_state")
    @Enumerated(value = EnumType.STRING)
    private BookState bookState;

}
