package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.deal.AddDealDto;
import com.iscourse.api.controller.dto.deal.DealAdminConditionDto;
import com.iscourse.api.domain.deal.dto.DealAdminListDto;
import com.iscourse.api.domain.deal.dto.DealDto;
import com.iscourse.api.domain.deal.dto.DealListDto;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.repository.deal.DealQueryRepository;
import com.iscourse.api.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DealController {
    private final DealQueryRepository dealQueryRepository;
    private final DealService dealService;

    @GetMapping("deal")
    public List<DealListDto> frontList() {
        return dealQueryRepository.findList();
    }

    @GetMapping("deal/{id}")
    public DealDto detail(@PathVariable("id") Long id) {
        return dealQueryRepository.findOne(id);
    }

    @PostMapping("purchase-deal/{id}")
    public void purchase(@AuthenticationPrincipal MemberLoginDto memberLoginDto, @PathVariable("id") Long dealId) {
        dealService.purchase(memberLoginDto.getId(), dealId);
    }

    // 매니저 권한

    @GetMapping("manager/deal")
    public Page<DealAdminListDto> adminList(@RequestBody DealAdminConditionDto condition, Pageable pageable) {
        return dealQueryRepository.findAdminList(condition.getName(), condition.getMinPrice(), condition.getMaxPrice(), pageable);
    }

    @PostMapping("manager/deal")
    public void add(@RequestPart("files") List<MultipartFile> files, @RequestPart("data") AddDealDto addDealDto) throws IOException {
        dealService.add(files, addDealDto);
    }

    @PatchMapping("manager/deal/{id}")
    public void update(@PathVariable("id") Long id, @RequestPart("files") List<MultipartFile> files, @RequestPart("data") AddDealDto addDealDto) throws IOException {
        dealService.update(id, files, addDealDto);
    }

}
