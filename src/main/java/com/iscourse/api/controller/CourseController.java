package com.iscourse.api.controller;

import com.iscourse.api.domain.course.dto.CourseFrontDto;
import com.iscourse.api.domain.course.dto.CourseFrontListDto;
import com.iscourse.api.domain.member.MemberPlace;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.repository.course.CourseQueryRepository;
import com.iscourse.api.repository.member.MemberCourseQueryRepository;
import com.iscourse.api.repository.member.MemberPlaceQueryRepository;
import com.iscourse.api.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CourseController {

    private final CourseQueryRepository courseQueryRepository;
    private final CourseService courseService;
    private final MemberCourseQueryRepository memberCourseQueryRepository;
    private final MemberPlaceQueryRepository memberPlaceQueryRepository;

    @GetMapping("course")
    public Page<CourseFrontListDto> frontList(Pageable pageable) {
        return courseQueryRepository.frontList(pageable);
    }

    @GetMapping("course/{id}")
    public CourseFrontDto frontDetail(@PathVariable("id") Long id) {
        return courseQueryRepository.frontDetail(id);
    }

    @PatchMapping("course-like/{id}")
    public void like(@PathVariable("id") Long courseId, @AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        courseService.like(courseId, memberLoginDto.getId());
    }

    @GetMapping("user/course")
    public Page<CourseFrontListDto> getMemberCourseList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return memberCourseQueryRepository.getMemberCourseList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("user/place")
    public Page<MemberPlace> getMemberPlaceList(Pageable pageable) {
        return memberPlaceQueryRepository.getMemberPlaceList(pageable);
    }

    @PostMapping("select-course/{id}")
    public void selectCourse(@PathVariable("id") Long courseId, @AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        courseService.selectCourse(courseId, memberLoginDto.getId());
    }
}
