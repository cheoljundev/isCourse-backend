package com.iscourse.api.domain.course;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LargeCategory {
    @Id
    @Column(name = "large_category_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PlaceType parent;

    public LargeCategory(String code, String name, PlaceType parent) {
        this.code = code;
        this.name = name;
        this.parent = parent;
    }
}
