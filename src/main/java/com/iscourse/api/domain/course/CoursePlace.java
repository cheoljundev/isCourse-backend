package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;

@Entity
public class CoursePlace extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "course_place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private int position;
}
