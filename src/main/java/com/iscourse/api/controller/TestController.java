package com.iscourse.api.controller;

import com.iscourse.api.domain.member.dto.MemberLoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @ResponseBody
    @GetMapping("/api/session-test")
    public void sessionTest(@AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        System.out.println("authentication = " + authentication);
        System.out.println("memberContext = " + memberLoginDto);
    }
}
