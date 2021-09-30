package com.andersen.library.services.user.impl;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Immutable
@Data
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "seq_role", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}

