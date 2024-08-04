package com.iscourse.api.domain.deal.dto;

import com.iscourse.api.domain.deal.Deal;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DealAdminListDto {
    private Long id;
    private String name;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public DealAdminListDto(Deal deal) {
        this.id = deal.getId();
        this.name = deal.getName();
        this.price = deal.getPrice();
        this.createdAt = deal.getCreatedAt();
        this.updatedAt = deal.getUpdatedAt();
    }
}
