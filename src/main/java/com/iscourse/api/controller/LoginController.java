package com.iscourse.api.controller;

import com.iscourse.api.dto.LoginRequest;
import com.iscourse.api.dto.member.SignUpMemberDto;
import com.iscourse.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginRequest loginRequest) {
        // swagger 문서화를 위한 api, 실제 내용은 RestAuthenticationFilter 확인.
    }
}
