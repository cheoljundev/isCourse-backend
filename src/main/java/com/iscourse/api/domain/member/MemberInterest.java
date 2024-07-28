package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.Tag;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberInterest extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_interest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setMember(Member member) {
        this.member = member;
    }

    private void setTag(Tag smallCategory) {
        this.tag = tag;
    }

    public static MemberInterest create(Tag tag) {
        MemberInterest memberInterest = new MemberInterest();
        memberInterest.setTag(tag);
        return memberInterest;
    }
}
