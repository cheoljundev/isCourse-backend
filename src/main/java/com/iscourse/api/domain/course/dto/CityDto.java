package com.iscourse.api.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CityDto {
    private String code;
    private String name;

    @QueryProjection
    public CityDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
