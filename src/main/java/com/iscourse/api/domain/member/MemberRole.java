package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberRole extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    // enum으로 롤 이름 설정?
    @Enumerated(EnumType.STRING)
    private MemberRoleType roleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private void setRoleType(MemberRoleType roleType) {
        this.roleType = roleType;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public static MemberRole create(MemberRoleType roleType) {
        MemberRole memberRole = new MemberRole();
        memberRole.setRoleType(roleType);
        return memberRole;
    }
}
