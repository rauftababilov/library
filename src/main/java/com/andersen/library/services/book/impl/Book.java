package com.andersen.library.services.book.impl;

import com.andersen.library.jpa.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class Book extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "seq_book", allocationSize = 10)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publish_year", nullable = false)
    private Integer publishYear;

    @ElementCollection
    @CollectionTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "author_id")
    private List<Long> authorIds = new ArrayList<>();

    @Column(name = "publishing_house_id", nullable = false)
    private Long publishingHouseId;

    @Column(name = "deleted")
    private boolean deleted;

}
