package com.iscourse.api.domain.member.dto;

import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.MemberRole;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberAdminDetailDto {
    private Long id;
    private String username;
    private String nickname;
    private GenderType genderType;
    private MemberRole memberRole;

    @QueryProjection
    public MemberAdminDetailDto(Long id, String username, String nickname, GenderType genderType) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.genderType = genderType;
    }
}
