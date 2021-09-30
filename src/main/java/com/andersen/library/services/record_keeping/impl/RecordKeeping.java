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
@NoArgsConstructor
@Table(name = "record_keeping")
public class RecordKeeping extends FullAuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_keeping_generator")
    @SequenceGenerator(name = "record_keeping_generator", sequenceName = "seq_record_keeping", allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private BookState bookState;
}
