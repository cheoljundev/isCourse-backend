package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.course.Course;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCourse extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_course_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public MemberCourse(Member member, Course course) {
        this.member = member;
        this.course = course;
    }
}
