package com.iscourse.api.domain.course;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class LargeCategory {
    @Id
    @Column(name = "large_category_id")
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PlaceType parent;

}
