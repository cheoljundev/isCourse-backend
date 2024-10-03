package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CourseAdminListDto {
    private Long id;
    private String name;
    private String image;
    private MemberRoleType courseType;

    @QueryProjection
    public CourseAdminListDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.courseType = course.getCourseType();
    }
}
