package com.iscourse.api.controller.dto.member;

import com.iscourse.api.domain.member.MemberRoleType;
import lombok.Data;

@Data
public class UpdateMemberRole {
    private MemberRoleType memberRoleType;
}
