package com.iscourse.api.controller;

import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.controller.dto.login.LoginRequest;
import com.iscourse.api.dto.member.SignUpMemberDto;
import com.iscourse.api.security.jwt.JwtUtil;
import com.iscourse.api.security.token.RestAuthenticationToken;
import com.iscourse.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class LoginController {
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.OK)
    public void signup(@RequestBody SignUpMemberDto sIgnUpMemberDto) {
        memberService.signup(sIgnUpMemberDto);
    }

    @PostMapping("signin")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new RestAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // 인증 성공 후, 사용자 세부정보를 로드하여 JWT 생성
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt); // JWT 토큰을 클라이언트에 반환
        } catch (AuthenticationException e) {
            // 인증 실패 시, 401 Unauthorized 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("tag")
    public List<TagDto> getTags() {
        return memberService.getTags();
    }
}
