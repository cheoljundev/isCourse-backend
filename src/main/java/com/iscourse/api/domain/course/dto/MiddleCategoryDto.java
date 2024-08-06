package com.iscourse.api.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MiddleCategoryDto {
    private String code;
    private String name;

    @QueryProjection
    public MiddleCategoryDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
