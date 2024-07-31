package com.iscourse.api.domain.member;

import com.iscourse.api.domain.course.Course;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class MemberCourse {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
