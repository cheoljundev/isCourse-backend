package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CourseFrontListDto {
    private Long id;
    private String name;
    private String time;
    private String state;
    private String image;

    @QueryProjection
    public CourseFrontListDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.time = course.getHour() + "시간 " + course.getMinute() + "분";
    }
}
