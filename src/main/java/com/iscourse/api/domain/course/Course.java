package com.iscourse.api.domain.course;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.MemberRoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    @Lob
    private String introduce;

    @Enumerated(EnumType.STRING)
    private MemberRoleType courseType;

    public Course(Integer hour, Integer minute, String introduce, MemberRoleType courseType) {
        this.hour = hour;
        this.minute = minute;
        this.introduce = introduce;
        this.courseType = courseType;
    }

}
