package com.iscourse.api.domain.deal;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity @Getter
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
    private String contact;
    private String opening;
    private String closing;
    private boolean parking;
}
