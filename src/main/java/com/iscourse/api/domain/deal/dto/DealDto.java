package com.iscourse.api.domain.deal.dto;

import com.iscourse.api.domain.deal.Deal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DealDto {
    private String station;
    private String name;
    private String product;
    private Integer beforePrice;
    private Integer price;
    private Integer discountRate;
    private String opening;
    private String closing;
    private String address1;
    private String address2;
    private String contact;
    private String mapx;
    private String mapy;
    private boolean parking;

    public DealDto(Deal deal) {
        this.station = deal.getStation();
        this.name = deal.getName();
        this.product = deal.getProduct();
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
