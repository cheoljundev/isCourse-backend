package com.iscourse.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.security.token.RestAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public RestAuthenticationFilter(HttpSecurity http) {
        super(new AntPathRequestMatcher("/api/signin", "POST"));
        setSecurityContextRepository(getSecurityContextRepository(http));
    }

    private SecurityContextRepository getSecurityContextRepository(HttpSecurity http) {
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        if (securityContextRepository == null) {
            securityContextRepository = new DelegatingSecurityContextRepository(
                    new RequestAttributeSecurityContextRepository(),
                    new HttpSessionSecurityContextRepository()
            );
        }
        return securityContextRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new IllegalArgumentException("Authentication method is not supported");
        }

        MemberLoginDto memberLoginDto = objectMapper.readValue(request.getReader(), MemberLoginDto.class);

        if (!StringUtils.hasText(memberLoginDto.getUsername()) || !StringUtils.hasText(memberLoginDto.getPassword())) {
            throw new AuthenticationServiceException("Username or Password  is not provided");
        }

        RestAuthenticationToken restAuthenticationToken = new RestAuthenticationToken(memberLoginDto.getUsername(), memberLoginDto.getPassword());

        return getAuthenticationManager().authenticate(restAuthenticationToken);

    }
}
