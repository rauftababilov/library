package com.andersen.library.services.client.impl;

import com.andersen.library.jpa.domain.base.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Client extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "seq_client", allocationSize = 10)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

}
