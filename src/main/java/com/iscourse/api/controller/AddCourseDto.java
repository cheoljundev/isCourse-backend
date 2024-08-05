package com.iscourse.api.controller;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class AddCourseDto {
    private String name;
    private Integer hour;
    private Integer minute;
    private String introduce;
    private Set<String> tagList = new HashSet<>();
    private List<Long> placeIdList;
}
