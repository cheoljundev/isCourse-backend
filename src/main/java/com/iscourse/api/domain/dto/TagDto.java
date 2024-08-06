package com.iscourse.api.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TagDto {
    private String code;
    private String name;

    @QueryProjection
    public TagDto(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
