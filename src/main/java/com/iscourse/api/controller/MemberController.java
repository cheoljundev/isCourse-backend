package com.iscourse.api.controller;

import com.iscourse.api.domain.member.MemberCourse;
import com.iscourse.api.repository.member.MemberCourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCourseQueryRepository memberCourseQueryRepository;

    @GetMapping("/user/course")
    public Page<MemberCourse> getMemberCourseList(Pageable pageable) {
        return memberCourseQueryRepository.getMemberCourseList(pageable);
    }

}
