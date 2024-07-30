package com.iscourse.api.controller;

import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.dto.LoginRequest;
import com.iscourse.api.dto.member.SignUpMemberDto;
import com.iscourse.api.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {
    private final MemberService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signup(@RequestBody SignUpMemberDto sIgnUpMemberDto) {
        memberService.signup(sIgnUpMemberDto);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginRequest loginRequest) {
        // swagger 문서화를 위한 api, 실제 내용은 RestAuthenticationFilter 확인.
    }

    @GetMapping("/signout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "signout";
    }

    @GetMapping("/tag")
    public List<TagDto> getTags() {
        return memberService.getTags();
    }
}
