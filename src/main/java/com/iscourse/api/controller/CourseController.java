package com.iscourse.api.controller;

import com.iscourse.api.domain.course.dto.CourseFrontDto;
import com.iscourse.api.repository.course.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CourseController {

    private final CourseQueryRepository courseQueryRepository;

    @GetMapping("/course/{id}")
    public CourseFrontDto frontDetail(@PathVariable("id") Long id) {
        return courseQueryRepository.frontDetail(id);
    }
}
