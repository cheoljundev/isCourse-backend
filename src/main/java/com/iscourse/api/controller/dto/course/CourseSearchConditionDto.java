package com.iscourse.api.controller.dto.course;

import com.iscourse.api.domain.member.MemberRoleType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CourseSearchConditionDto {
    private String name;
    private Set<String> tagList = new HashSet<>();
    private MemberRoleType courseType;
}
