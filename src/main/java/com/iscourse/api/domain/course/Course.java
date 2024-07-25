package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.MemberRoleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    @Column(name = "hours")
    private Integer hour;

    @Column(name = "minutes")
    private Integer minute;

    @OneToMany(mappedBy = "course")
    private List<CourseTag> tags = new ArrayList<>();

    @Lob
    private String introduce;

    @OneToMany(mappedBy = "course")
    private List<CoursePlace> placeList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberRoleType courseType;
}
