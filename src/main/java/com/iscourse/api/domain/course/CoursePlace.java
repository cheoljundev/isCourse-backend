package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePlace extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "course_place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private int position;

    public void setCourse(Course course) {
        this.course = course;
    }

    public CoursePlace(Course course, Place place, int position) {
        this.course = course;
        this.place = place;
        this.position = position;
        course.setAxis(place.getMapx(), place.getMapy());
    }

    public void delete() {
        this.enabled = false;
    }
}
