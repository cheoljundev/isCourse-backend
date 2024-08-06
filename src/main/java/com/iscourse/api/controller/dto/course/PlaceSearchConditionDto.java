package com.iscourse.api.controller.dto.course;

import lombok.Data;

@Data
public class PlaceSearchConditionDto {
    private String placeTypeCode;
    private String largeCategoryCode;
    private String middleCategoryCode;
    private String tagCode;
    private String stateCode;
    private String cityCode;
    private String name;
}
