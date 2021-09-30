package com.andersen.library.services.user.impl;

import com.andersen.library.security.PredefinedRole;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Immutable
@Data
class Role {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private PredefinedRole name;

}

