package com.iscourse.api.domain.course.dto;

import com.iscourse.api.domain.member.MemberRoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
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
