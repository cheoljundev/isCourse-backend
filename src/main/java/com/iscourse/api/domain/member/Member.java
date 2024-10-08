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

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String nickname;

    @Embedded
    private SocialInfo socialInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public void delete() {
        this.enabled = false;
    }

}
