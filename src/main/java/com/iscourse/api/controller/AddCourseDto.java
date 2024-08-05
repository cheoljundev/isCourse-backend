package com.iscourse.api.controller;

import lombok.Data;

import java.util.List;

@Data
public class AddCourseDto {
    private String name;
    private Integer hour;
    private Integer minute;
    private String introduce;
    private List<Long> placeIdList;
}
