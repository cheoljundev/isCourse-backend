package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseFrontListDto {
    private Long id;
    private String name;
    private String time;
    private String state;
    private String image;
    private MemberRoleType courseType;
    private Integer likes;
    private List<String> tags = new ArrayList<>();

    @QueryProjection
    public CourseFrontListDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.time = course.getHour() + "시간 " + course.getMinute() + "분";
        this.courseType = course.getCourseType();
        this.likes = course.getLikes();
    }
}
