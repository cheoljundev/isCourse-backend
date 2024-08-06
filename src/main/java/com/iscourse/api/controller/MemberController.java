package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.member.MemberSearchCondition;
import com.iscourse.api.controller.dto.member.UpdateMemberRole;
import com.iscourse.api.domain.member.MemberRoleType;
import com.iscourse.api.domain.member.dto.MemberAdminDetailDto;
import com.iscourse.api.domain.member.dto.MemberListDto;
import com.iscourse.api.repository.member.MemberQueryRepository;
import com.iscourse.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberService memberService;

    @GetMapping("admin/member")
    public Page<MemberListDto> getMemberList(@ModelAttribute MemberSearchCondition condition, Pageable pageable) {
        return memberQueryRepository.getMemberList(condition, pageable);
    }

    @GetMapping("admin/member/{id}")
    public MemberAdminDetailDto getMember(@PathVariable Long id) {
        return memberQueryRepository.getMember(id);
    }

    @PatchMapping("admin/member/{id}")
    public void updateRole(@PathVariable Long id, @RequestBody UpdateMemberRole updateMemberRole) {
        memberService.updateRole(id, updateMemberRole);
    }

}
