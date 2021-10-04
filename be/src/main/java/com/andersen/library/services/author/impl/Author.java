package com.andersen.library.services.author.impl;

import com.andersen.library.jpa.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class Author extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name = "author_generator", sequenceName = "seq_author", allocationSize = 10)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "deleted")
    private boolean deleted;

}
