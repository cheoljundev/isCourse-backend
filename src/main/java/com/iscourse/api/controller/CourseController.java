package com.iscourse.api.controller;

import com.iscourse.api.domain.course.dto.CourseFrontDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/course/{id}")
    public CourseFrontDto frontDetail(@PathVariable("id") Long id) {
//        return courseService.frontDetail(id);
        return null;
    }
}
