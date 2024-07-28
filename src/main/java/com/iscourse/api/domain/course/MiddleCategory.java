package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiddleCategory{
    @Id
    @Column(name = "middle_category_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private LargeCategory parent;

    public MiddleCategory(String code, String name, LargeCategory parent) {
        this.code = code;
        this.name = name;
        this.parent = parent;
    }
}
