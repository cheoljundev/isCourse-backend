package com.iscourse.api.domain.member.dto;

import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.MemberRole;
import com.iscourse.api.domain.member.MemberRoleType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;

@Data
public class MemberListDto {
    private Long id;
    private String username;
    private String nickname;
    private GenderType genderType;
    private List<MemberRoleType> roles;

    @QueryProjection
    public MemberListDto(Long id, String username, String nickname, GenderType genderType) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.genderType = genderType;
    }
}
