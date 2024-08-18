package com.iscourse.api.domain.member.dto;

import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberRole;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberEditInfoDto {
    private Long id;
    private String username;
    private String nickname;
    private List<TagDto> tags = new ArrayList<>();

    @QueryProjection
    public MemberEditInfoDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }
}
