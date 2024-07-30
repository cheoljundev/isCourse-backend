package com.iscourse.api.controller;

import com.iscourse.api.domain.course.dto.CourseFrontDto;
import com.iscourse.api.domain.course.dto.CourseFrontListDto;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.repository.course.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CourseController {

    private final CourseQueryRepository courseQueryRepository;

    @GetMapping("/course")
    public Page<CourseFrontListDto> frontList(Pageable pageable) {
        return courseQueryRepository.frontList(pageable);
    }

    @GetMapping("/course/{id}")
    public CourseFrontDto frontDetail(@PathVariable("id") Long id) {
        return courseQueryRepository.frontDetail(id);
    }
}
