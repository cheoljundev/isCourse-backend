package com.iscourse.api.domain.deal.dto;

import lombok.Data;

@Data
public class AddDealDto {
    private String station;
    private String name;
    private String product;
    private Integer beforePrice;
    private Integer price;
    private String opening;
    private String closing;
    private String address1;
    private String address2;
    private String contact;
    private Double mapx;
    private Double mapy;
    private boolean parking;
}
