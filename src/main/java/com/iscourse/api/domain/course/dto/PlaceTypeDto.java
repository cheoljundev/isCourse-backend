package com.iscourse.api.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PlaceTypeDto {
    private String code;
    private String name;

    @QueryProjection
    public PlaceTypeDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
