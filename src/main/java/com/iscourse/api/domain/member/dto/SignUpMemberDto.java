package com.iscourse.api.domain.member.dto;

import com.iscourse.api.domain.member.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMemberDto {
    private String username;
    private String password;
    private GenderType gender;
    private String nickname;
    private List<Long> interests = new ArrayList<>();
}
