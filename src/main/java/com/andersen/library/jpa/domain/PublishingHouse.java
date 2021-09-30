package com.andersen.library.jpa.domain;


import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publishing_house")
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(of = "id", callSuper = true)
public class PublishingHouse extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publishing_house_generator")
    @SequenceGenerator(name = "publishing_house_generator", sequenceName = "seq_publishing_house", allocationSize = 10)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publishingHouse", cascade = CascadeType.REMOVE)
    private List<Book> books = new ArrayList<>();

}
