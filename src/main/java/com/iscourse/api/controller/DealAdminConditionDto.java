package com.iscourse.api.controller;

import lombok.Data;

@Data
public class DealAdminConditionDto {
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
}
