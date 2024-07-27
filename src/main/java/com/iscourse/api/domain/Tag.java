package com.iscourse.api.domain;

import com.iscourse.api.domain.course.MiddleCategory;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class Tag{

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MiddleCategory parent;

}
