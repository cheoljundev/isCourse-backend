package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseTag extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "course_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public CourseTag(Course course, Tag tag) {
        this.course = course;
        this.tag = tag;
    }
}
