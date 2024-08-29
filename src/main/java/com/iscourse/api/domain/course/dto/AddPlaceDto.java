package com.iscourse.api.domain.course.dto;

import lombok.Data;

@Data
public class AddPlaceDto {
    private String title;
    private String placeTypeCode;
    private String largeCategoryCode;
    private String middleCategoryCode;
    private String tagCode;
    private String stateCode;
    private String cityCode;
    private String address1;
    private String address2;
    private String zipcode;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String image;

}
