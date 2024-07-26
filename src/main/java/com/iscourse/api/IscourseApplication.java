package com.iscourse.api;

import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class IscourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(IscourseApplication.class, args);
    }

    @Bean
    public AuditorAware<Member> auditorProvider() {
        return () -> null;
    }

}
