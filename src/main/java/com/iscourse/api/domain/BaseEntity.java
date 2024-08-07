package com.iscourse.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iscourse.api.domain.member.Member;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(name= "created_by")
    @JsonIgnore
    private Member createdBy;

    @JsonIgnore
    protected Boolean enabled = true;

}
