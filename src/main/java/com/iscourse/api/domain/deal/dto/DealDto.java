package com.iscourse.api.domain.deal.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.dto.UploadFileDto;
import com.iscourse.api.formatter.PriceSerializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;

@Data
public class DealDto {
    private String station;
    private String name;
    private String product;
    private String description;
    @JsonSerialize(using = PriceSerializer.class)
    private Integer beforePrice;
    @JsonSerialize(using = PriceSerializer.class)
    private Integer price;
    private Integer discountRate;
    private String opening;
    private String closing;
    private String address1;
    private String address2;
    private String contact;
    private Double mapx;
    private Double mapy;
    private boolean parking;
    private List<UploadFileDto> images;

    @QueryProjection
    public DealDto(Deal deal) {
        this.station = deal.getStation();
        this.name = deal.getName();
        this.product = deal.getProduct();
        this.description = deal.getDescription();
        this.beforePrice = deal.getBeforePrice();
        this.price = deal.getPrice();
        this.discountRate = deal.getDiscountRate();
        this.opening = deal.getOpening();
        this.closing = deal.getClosing();
        this.address1 = deal.getAddress1();
        this.address2 = deal.getAddress2();
        this.contact = deal.getContact();
        this.mapx = deal.getMapx();
        this.mapy = deal.getMapy();
        this.parking = deal.getParking();
    }
}
