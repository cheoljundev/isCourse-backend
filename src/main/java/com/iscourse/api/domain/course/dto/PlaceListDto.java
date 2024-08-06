package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.course.*;
import com.iscourse.api.domain.member.MemberPlace;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PlaceListDto {
    private String name;
    private PlaceType placeType;
    private LargeCategory largeCategory;
    private MiddleCategory middleCategory;
    private Tag tag;
    private State state;
    private City city;
    private String address1;
    private String address2;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String image;

    @QueryProjection
    public PlaceListDto(MemberPlace memberPlace) {
        this.name = memberPlace.getPlace().getName();
        this.placeType = memberPlace.getPlace().getPlaceType();
        this.largeCategory = memberPlace.getPlace().getLargeCategory();
        this.middleCategory = memberPlace.getPlace().getMiddleCategory();
        this.tag = memberPlace.getPlace().getTag();
        this.state = memberPlace.getPlace().getState();
        this.city = memberPlace.getPlace().getCity();
        this.address1 = memberPlace.getPlace().getAddress1();
        this.address2 = memberPlace.getPlace().getAddress2();
        this.mapx = memberPlace.getPlace().getMapx();
        this.mapy = memberPlace.getPlace().getMapy();
        this.tel = memberPlace.getPlace().getTel();
        this.image = memberPlace.getPlace().getImage();
    }
}
