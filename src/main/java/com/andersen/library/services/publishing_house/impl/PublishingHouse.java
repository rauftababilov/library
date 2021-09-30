package com.andersen.library.services.publishing_house.impl;


import com.andersen.library.jpa.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "publishing_house")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class PublishingHouse extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publishing_house_generator")
    @SequenceGenerator(name = "publishing_house_generator", sequenceName = "seq_publishing_house", allocationSize = 10)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "deleted")
    private boolean deleted;

}
