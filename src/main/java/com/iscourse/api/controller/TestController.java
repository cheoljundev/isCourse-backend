package com.iscourse.api.controller;

import com.iscourse.api.domain.member.dto.MemberLoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/session-test")
    public MemberLoginDto sessionTest(@AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        return memberLoginDto;
    }
}
