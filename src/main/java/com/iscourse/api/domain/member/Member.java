package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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



    public Member(String username, String password, GenderType gender, String nickname) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.nickname = nickname;
    }

    public MemberRole addRole(MemberRole memberRole) {
        memberRoles.add(memberRole);
        memberRole.setMember(this);
        return memberRole;
    }

}
