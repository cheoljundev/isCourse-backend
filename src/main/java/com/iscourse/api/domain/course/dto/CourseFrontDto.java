package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.course.CoursePlace;
import com.iscourse.api.domain.course.CourseTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFrontDto {
    private Integer hour;

    private Integer minute;

    private List<CourseTag> tags;

    private String introduce;

    private List<CoursePlaceDto> placeList;

    private String image;
}
