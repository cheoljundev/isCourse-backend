package com.iscourse.api.controller;

import lombok.Data;

import java.util.List;

@Data
public class CourseShareDto {
    private String name;
    private Integer hour;
    private Integer minute;
    private String introduce;
    private List<Long> placeIdList;
}
