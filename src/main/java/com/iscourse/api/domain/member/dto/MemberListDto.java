package com.iscourse.api.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberListDto {
    private Long id;
    private String username;
    private String nickname;

    @QueryProjection
    public MemberListDto(Long id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
}
