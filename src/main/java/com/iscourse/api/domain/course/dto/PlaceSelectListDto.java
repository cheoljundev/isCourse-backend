package com.iscourse.api.domain.course.dto;

import lombok.Data;

@Data
public class PlaceSelectListDto {
    private String address1;
    private String address2;
    private String stateCode;
    private String cityCode;
    private String largeCategoryCode;
    private String middleCategoryCode;
    private String tagCode;
    private String placeTypeCode;
    private String image;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String title;
    private String zipcode;

    public PlaceSelectListDto(ApiResponse.Item item) {
        this.address1 = item.getAddress1();
        this.address2 = item.getAddress2();
        this.stateCode = item.getStateCode();
        this.cityCode = item.getCityCode();
        this.largeCategoryCode = item.getLargeCategoryCode();
        this.middleCategoryCode = item.getMiddleCategoryCode();
        this.tagCode = item.getTagCode();
        this.placeTypeCode = item.getPlaceTypeCode();
        this.image = item.getImage();
        this.mapx = Double.valueOf(item.getMapx());
        this.mapy = Double.valueOf(item.getMapy());
        this.tel = item.getTel();
        this.title = item.getName();
        this.zipcode = item.getZipcode();
    }
}
