package com.iscourse.api.domain.member.dto;
import com.iscourse.api.domain.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberEditInfoUpdateDto {
    private String password;
    private String nickname;
    private List<String> tags = new ArrayList<>();
}
