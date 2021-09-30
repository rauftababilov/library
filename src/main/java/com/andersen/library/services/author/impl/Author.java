package com.andersen.library.services.author.impl;


import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import com.andersen.library.services.book.impl.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(of = "id", callSuper = true)
public class Author extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name = "author_generator", sequenceName = "seq_author", allocationSize = 10)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ElementCollection
    @CollectionTable(name = "book_author", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "book_id")
    private List<Long> bookIds = new ArrayList<>();
}
