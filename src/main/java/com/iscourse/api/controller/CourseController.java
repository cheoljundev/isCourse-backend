package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.course.AddCourseDto;
import com.iscourse.api.controller.dto.course.CourseSearchConditionDto;
import com.iscourse.api.controller.dto.course.RecommendCourseConditionDto;
import com.iscourse.api.domain.course.dto.CourseAdminDto;
import com.iscourse.api.domain.course.dto.CourseAdminListDto;
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

    @PostMapping("select-course/{id}")
    public void select(@PathVariable("id") Long courseId, @AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        courseService.selectCourse(courseId, memberLoginDto.getId());
    }

    @PostMapping("share-course")
    public void share(@RequestBody AddCourseDto addCourseDto) {
        courseService.share(addCourseDto);
    }

    @GetMapping("recommend-course")
    public Page<CourseFrontListDto> recommend(@RequestBody RecommendCourseConditionDto condition) {
        return courseQueryRepository.recommendCourse(condition.getMapx(), condition.getMapy(), condition.getMaxDistance());
    }

    @GetMapping("user/course")
    public Page<CourseFrontListDto> getMemberSelectedList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return memberCourseQueryRepository.getMemberSelectedList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("user/place")
    public Page<MemberPlace> getMemberPlaceList(Pageable pageable) {
        return memberPlaceQueryRepository.getMemberPlaceList(pageable);
    }

    @GetMapping("user/shared-course")
    public Page<CourseFrontListDto> getMemberSharedList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return courseQueryRepository.getMemberSharedList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("manager/course")
    public Page<CourseAdminListDto> adminList(@RequestBody CourseSearchConditionDto condition, Pageable pageable) {
        return courseQueryRepository.adminList(condition, pageable);
    }

    @GetMapping("manager/course/{id}")
    public CourseAdminDto adminDetail(@PathVariable("id") Long id) {
        return courseQueryRepository.adminDetail(id);
    }

    @PostMapping("manager/course")
    public void addCourse(@RequestBody AddCourseDto addCourseDto) {
        courseService.addCourse(addCourseDto);
    }

    @PatchMapping("manager/course/{id}")
    public void updateCourse(@PathVariable("id") Long id, @RequestBody AddCourseDto addCourseDto) {
        courseService.updateCourse(id, addCourseDto);
    }


}
