package com.andersen.library.services.record_keeping.impl;

import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import com.andersen.library.jpa.domain.enums.BookState;
import com.andersen.library.services.book.impl.Book;
import com.andersen.library.services.client.impl.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "record_keeping")
public class RecordKeeping extends FullAuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_keeping_generator")
    @SequenceGenerator(name = "record_keeping_generator", sequenceName = "seq_record_keeping", allocationSize = 20)
    private Long id;

    @JoinColumn(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "book_state")
    @Enumerated(value = EnumType.STRING)
    private BookState bookState;
}