package com.iscourse.api.domain.deal.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iscourse.api.domain.deal.Deal;
import com.iscourse.api.domain.dto.UploadFileDto;
import com.iscourse.api.formatter.PriceSerializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class DealListDto {
    private Long id;
    private String station;
    private String name;
    private String product;
    @JsonSerialize(using = PriceSerializer.class)
    private Integer price;
    private Integer discountRate;
    private UploadFileDto image;

    @QueryProjection
    public DealListDto(Deal deal, UploadFileDto image) {
        this.id = deal.getId();
        this.station = deal.getStation();
        this.name = deal.getName();
        this.product = deal.getProduct();
        this.price = deal.getPrice();
        this.discountRate = deal.getDiscountRate();
        this.image = image;
    }
}
