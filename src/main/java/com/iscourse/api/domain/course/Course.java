package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.MemberRoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long id;

    @Column(name = "hours")
    private Integer hour;

    @Column(name = "minutes")
    private Integer minute;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseTag> tags = new ArrayList<>();

    @Lob
    private String introduce;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CoursePlace> placeList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberRoleType courseType;

    public Course(Integer hour, Integer minute, String introduce, MemberRoleType courseType) {
        this.hour = hour;
        this.minute = minute;
        this.introduce = introduce;
        this.courseType = courseType;
    }

    public void addCourseTag(CourseTag courseTag) {
        tags.add(courseTag);
        courseTag.setCourse(this);
    }

    public void addCoursePlace(CoursePlace coursePlace) {
        placeList.add(coursePlace);
        coursePlace.setCourse(this);
    }
}
