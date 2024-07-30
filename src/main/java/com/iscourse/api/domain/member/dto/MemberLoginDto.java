package com.iscourse.api.domain.member.dto;

import com.iscourse.api.domain.member.MemberRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberLoginDto {
    private Long id;
    private String username;

    private String password;

    private List<MemberRole> memberRoles;

    private Boolean enabled;
}
