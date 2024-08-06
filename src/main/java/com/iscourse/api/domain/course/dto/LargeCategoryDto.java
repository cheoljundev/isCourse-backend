package com.iscourse.api.domain.course.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class LargeCategoryDto {
    private String code;
    private String name;

    @QueryProjection
    public LargeCategoryDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
