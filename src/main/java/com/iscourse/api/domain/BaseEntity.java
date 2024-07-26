package com.iscourse.api.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {

//    @CreatedBy
//    @ManyToOne
//    @JoinColumn(name= "created_by")
//    private Member createdBy;

    private boolean enabled = true;

}
