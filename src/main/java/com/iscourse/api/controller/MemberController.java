package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.member.MemberSearchCondition;
import com.iscourse.api.domain.member.dto.MemberListDto;
import com.iscourse.api.repository.member.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryRepository memberQueryRepository;

    @GetMapping("admin/member")
    public Page<MemberListDto> getMemberList(@ModelAttribute MemberSearchCondition condition, Pageable pageable) {
        return memberQueryRepository.getMemberList(condition, pageable);
    }
}
