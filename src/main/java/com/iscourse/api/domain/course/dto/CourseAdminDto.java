package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseAdminDto {

    private String name;
    private Integer hours;
    private Integer minutes;
    private String introduce;
    private List<String> tags = new ArrayList<>();
    private List<CoursePlaceDto> coursePlaces = new ArrayList<>();

    @QueryProjection
    public CourseAdminDto(Course course) {
        this.name = course.getName();
        this.hours = course.getHour();
        this.minutes = course.getMinute();
        this.introduce = course.getIntroduce();
    }
}
