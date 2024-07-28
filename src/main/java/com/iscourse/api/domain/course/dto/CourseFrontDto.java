package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseFrontDto {

    private String name;
    private Integer hour;
    private Integer minute;
    private String introduce;
    private List<String> tags = new ArrayList<>();
    private List<CoursePlaceDto> coursePlaces = new ArrayList<>();
    private String image;

    @QueryProjection
    public CourseFrontDto(Course course) {
        this.name = course.getName();
        this.hour = course.getHour();
        this.minute = course.getMinute();
        this.introduce = course.getIntroduce();
    }
}
