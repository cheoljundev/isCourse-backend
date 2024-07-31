package com.iscourse.api.controller;

import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.domain.deal.dto.DealListDto;
import com.iscourse.api.repository.deal.DealQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DealController {
    private final DealQueryRepository dealQueryRepository;

    @GetMapping("deal")
    public List<DealListDto> frontList() {
        return dealQueryRepository.findList();
    }

    @GetMapping("deal/{id}")
    public DealDto frontDetail(@PathVariable("id") Long id) {
        return dealQueryRepository.findOne(id);
    }
}
