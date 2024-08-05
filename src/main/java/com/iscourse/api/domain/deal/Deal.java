package com.iscourse.api.domain.deal;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Deal extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "deal_id")
    private Long id;
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
    private Double mapx;
    private Double mapy;
    private Boolean parking;

    public Deal(String station, String name, String product, Integer beforePrice, Integer price, Integer discountRate, String opening, String closing, String address1, String address2, String contact, Double mapx, Double mapy, boolean parking) {
        this.station = station;
        this.name = name;
        this.product = product;
        this.beforePrice = beforePrice;
        this.price = price;
        this.discountRate = discountRate;
        this.opening = opening;
        this.closing = closing;
        this.address1 = address1;
        this.address2 = address2;
        this.contact = contact;
        this.mapx = mapx;
        this.mapy = mapy;
        this.parking = parking;
    }
}
