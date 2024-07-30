package com.iscourse.api.domain.member;


import com.iscourse.api.domain.course.Course;
import jakarta.persistence.*;

@Entity
public class MemberCourseLike {
    @Id @GeneratedValue
    @Column(name = "user_course_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public MemberCourseLike(Member member, Course course) {
        this.member = member;
        this.course = course;
    }
}
