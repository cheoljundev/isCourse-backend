package com.iscourse.api.domain.deal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iscourse.api.domain.deal.SalesDetail;
import com.iscourse.api.formatter.PriceSerializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SalesDetailDto {
    private Long id;
    private String dealName;
    private String username;
    @JsonSerialize(using = PriceSerializer.class)
    private Integer price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @QueryProjection
    public SalesDetailDto(SalesDetail salesDetail) {
        this.id = salesDetail.getId();
        this.dealName = salesDetail.getDeal().getName();
        this.username = salesDetail.getMember().getUsername();
        this.price = salesDetail.getDeal().getPrice();
        this.createdAt = salesDetail.getCreatedAt();
    }
}
