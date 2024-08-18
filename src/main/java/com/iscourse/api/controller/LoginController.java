package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.ValidateUsernameDto;
import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.controller.dto.login.LoginRequest;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.domain.member.dto.SignUpMemberDto;
import com.iscourse.api.repository.member.MemberQueryRepository;
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
    private final MemberQueryRepository memberQueryRepository;
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("validate-username")
    public ResponseEntity<Boolean> validateUsername(@RequestBody ValidateUsernameDto usernameDto) {
        return ResponseEntity.ok(memberQueryRepository.validateUsername(usernameDto.getUsername()));
    }

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
            MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(loginRequest.getUsername());

            if (!memberContext.isEnabled()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Disabled user");
            }

            String jwt = jwtUtil.generateToken(memberContext.getUsername());
            return ResponseEntity.ok(jwt); // JWT 토큰을 클라이언트에 반환
        } catch (AuthenticationException e) {
            // 인증 실패 시, 401 Unauthorized 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("check-token")
    public ResponseEntity<String> checkToken() {
        return ResponseEntity.ok("Token is valid");
    }

    @PostMapping("manager/check-token")
    public ResponseEntity<String> checkManagerToken() {
        return ResponseEntity.ok("Manager Token is valid");
    }

    @PostMapping("admin/check-token")
    public ResponseEntity<String> checkAdminToken() {
        return ResponseEntity.ok("Admin Token is valid");
    }

    @GetMapping("tag")
    public List<TagDto> getTags() {
        return memberService.getTags();
    }
}
