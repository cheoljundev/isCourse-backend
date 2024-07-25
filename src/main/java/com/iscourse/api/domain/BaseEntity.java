package com.iscourse.api.domain;

import com.iscourse.api.domain.member.Member;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @ManyToOne
    @JoinColumn(name= "created_by")
    private Member createdBy;

    private boolean enabled;

}
