package com.iscourse.api.controller;

import com.iscourse.api.controller.dto.course.*;
import com.iscourse.api.domain.course.dto.*;
import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.repository.course.CourseQueryRepository;
import com.iscourse.api.repository.course.PlaceQueryRepository;
import com.iscourse.api.repository.course.PlaceTypeRepository;
import com.iscourse.api.repository.member.MemberCourseQueryRepository;
import com.iscourse.api.repository.member.MemberPlaceQueryRepository;
import com.iscourse.api.service.CourseService;
import com.iscourse.api.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CourseController {

    private final CourseQueryRepository courseQueryRepository;
    private final CourseService courseService;
    private final MemberCourseQueryRepository memberCourseQueryRepository;
    private final MemberPlaceQueryRepository memberPlaceQueryRepository;
    private final PlaceQueryRepository placeQueryRepository;
    private final PlaceService placeService;
    private final PlaceTypeRepository placeTypeRepository;

    @GetMapping("course")
    public Page<CourseFrontListDto> frontList(Pageable pageable) {
        return courseQueryRepository.frontList(pageable);
    }

    @GetMapping("course/{id}")
    public CourseFrontDto frontDetail(@PathVariable("id") Long id) {
        return courseQueryRepository.frontDetail(id);
    }

    @PatchMapping("course-like/{id}")
    public Integer like(@PathVariable("id") Long courseId, @AuthenticationPrincipal MemberLoginDto memberLoginDto) {
        return courseService.like(courseId, memberLoginDto.getId());
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
    public Page<CourseFrontListDto> recommend(@ModelAttribute RecommendCourseConditionDto condition, Pageable pageable) {
        return courseQueryRepository.recommendCourse(condition.getMapx(), condition.getMapy(), condition.getMaxDistance(), pageable);
    }

    @GetMapping("user/course")
    public Page<CourseFrontListDto> getMemberSelectedList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return memberCourseQueryRepository.getMemberSelectedList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("user/place")
    public Page<PlaceListDto> getMemberPlaceList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return memberPlaceQueryRepository.getMemberPlaceList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("user/shared-course")
    public Page<CourseFrontListDto> getMemberSharedList(@AuthenticationPrincipal MemberLoginDto memberLoginDto, Pageable pageable) {
        return courseQueryRepository.getMemberSharedList(memberLoginDto.getId(), pageable);
    }

    @GetMapping("manager/course")
    public Page<CourseAdminListDto> adminList(@ModelAttribute CourseSearchConditionDto condition, Pageable pageable) {
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

    @DeleteMapping("manager/course/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("manager/place")
    public Page<PlaceListDto> adminPlaceList(@ModelAttribute PlaceSearchConditionDto condition, Pageable pageable) {
        return placeQueryRepository.adminList(condition, pageable);
    }

    @DeleteMapping("manager/place/{id}")
    public void deletePlace(@PathVariable("id") Long id) {
        placeService.deletePlace(id);
    }

    @GetMapping("manager/place/search")
    public Page<PlaceSelectListDto> getPlaceByApi(@ModelAttribute TourApiSearchConditionDto condition, Pageable pageable) {
        return placeService.getPlaceByApi(condition, pageable);
    }

    @GetMapping("manager/place-type")
    public List<PlaceTypeDto> getPlaceType() {
        return placeQueryRepository.getPlaceTypes();
    }

    @GetMapping("manager/large-category")
    public List<LargeCategoryDto> getLargeCategory(@RequestParam("parent") Long parentId) {
        return placeQueryRepository.getLargeCategory(parentId);
    }

    @GetMapping("manager/middle-category")
    public List<MiddleCategoryDto> getMiddleCategory(@RequestParam("parent") Long parentId) {
        return placeQueryRepository.getMiddleCategory(parentId);
    }

    @GetMapping("manager/tag")
    public List<TagDto> getTag(@RequestParam("parent") Long parentId) {
        return placeQueryRepository.getTags(parentId);
    }

    @GetMapping("manager/state")
    public List<StateDto> getState() {
        return placeQueryRepository.getState();
    }

    @GetMapping("manager/city")
    public List<CityDto> getCity(@RequestParam("parent") Long parentId) {
        return placeQueryRepository.getCity(parentId);
    }


}
