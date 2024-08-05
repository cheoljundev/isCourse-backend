package com.iscourse.api.controller.dto.deal;

import lombok.Data;

@Data
public class DealAdminConditionDto {
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
}
