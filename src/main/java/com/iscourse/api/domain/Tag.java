package com.iscourse.api.domain;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity @Getter
public class Tag extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String name;
}
