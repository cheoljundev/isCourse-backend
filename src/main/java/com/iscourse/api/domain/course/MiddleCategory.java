package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity @Getter
public class MiddleCategory{
    @Id
    @Column(name = "middle_category_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private LargeCategory parent;
}
