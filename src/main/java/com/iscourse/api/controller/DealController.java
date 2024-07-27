package com.iscourse.api.controller;

import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DealController {
    private final DealService dealService;

    @GetMapping("/deal/{id}")
    public DealDto detail(@PathVariable("id") Long id) {
        return dealService.findOne(id);
    }
}
