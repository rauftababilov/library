package com.andersen.library.services.client.impl;

import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Client extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "seq_client", allocationSize = 10)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;
}
