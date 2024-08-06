package com.iscourse.api.controller.dto.member;

import com.iscourse.api.domain.member.GenderType;
import lombok.Data;

@Data
public class MemberSearchCondition {
    private String username;
    private String nickname;
    private GenderType genderType;
}