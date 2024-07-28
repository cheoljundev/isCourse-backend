package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseFrontDto {

    private String name;
    private String time;
    private String introduce;
    private String state;
    private String nickname;
    private List<String> tags = new ArrayList<>();
    private List<CoursePlaceDto> coursePlaces = new ArrayList<>();
    private String image;
    private int likes;

    @QueryProjection
    public CourseFrontDto(Course course) {
        this.name = course.getName();
        this.time = course.getHour() + "시간 " + course.getMinute() + "분";
        this.introduce = course.getIntroduce();
        this.likes = course.getLikes();
        if (course.getCreatedBy() != null) {
            this.nickname = course.getCreatedBy().getNickname();
        }
    }
}
