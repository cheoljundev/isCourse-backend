package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private GenderType gender;

    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<MemberInterest> interests;

    @Embedded
    private SocialInfo socialInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<MemberRole> memberRoles = new ArrayList<>();

    public MemberRole addRole(MemberRole memberRole) {
        memberRoles.add(memberRole);
        memberRole.setMember(this);
        return memberRole;
    }

}
