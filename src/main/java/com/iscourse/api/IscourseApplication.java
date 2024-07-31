package com.iscourse.api;

import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class IscourseApplication {

    private final MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(IscourseApplication.class, args);
    }

    @Bean
    public AuditorAware<Member> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !(authentication.getPrincipal() instanceof MemberContext)) {
                return Optional.empty();
            }

            MemberContext principal = (MemberContext) authentication.getPrincipal();
            Long id = principal.getMemberLoginDto().getId();

            // Optional로 래핑하여 반환
            return memberRepository.findById(id);
        };
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
