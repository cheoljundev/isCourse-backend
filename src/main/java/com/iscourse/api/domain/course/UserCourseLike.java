package com.iscourse.api.domain.course;


import com.iscourse.api.domain.member.Member;
import jakarta.persistence.*;

@Entity
public class UserCourseLike {
    @Id @GeneratedValue
    @Column(name = "user_course_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
