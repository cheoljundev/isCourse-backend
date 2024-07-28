package com.iscourse.api.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CoursePlaceDto {
    private String state;
    private String name;
    private String image;

    private int position;

    @QueryProjection
    public CoursePlaceDto(String state, String name, String image, int position) {
        this.state = state;
        this.name = name;
        this.image = image;
        this.position = position;
    }
}
