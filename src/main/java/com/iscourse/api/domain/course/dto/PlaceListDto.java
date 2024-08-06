package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.member.MemberPlace;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PlaceListDto {
    private Long id;
    private String name;
    private String image;

    @QueryProjection
    public PlaceListDto(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
