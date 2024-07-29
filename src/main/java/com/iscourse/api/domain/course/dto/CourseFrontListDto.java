package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;

@Data
public class CourseFrontListDto {
    private Long id;
    private String name;
    private String time;
    private String state;
    private String image;
    private MemberRoleType courseType;

    @QueryProjection
    public CourseFrontListDto(Course course, String state, String image) {
        this.id = course.getId();
        this.name = course.getName();
        this.time = course.getHour() + "시간 " + course.getMinute() + "분";
        this.state = state;
        this.image = image;
        this.courseType = course.getCourseType();
    }
}
