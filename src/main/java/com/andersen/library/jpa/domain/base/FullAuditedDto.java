package com.andersen.library.jpa.domain.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class FullAuditedDto extends DateAuditedEntity {

    @Column(name = "created_by")
    @CreatedBy
    private Long createdBy;

    @Column(name = "updated_by")
    @LastModifiedBy
    private Long updatedBy;

}
