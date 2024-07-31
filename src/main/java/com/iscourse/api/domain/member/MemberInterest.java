package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public MemberInterest(Member member, Tag tag) {
        this.member = member;
        this.tag = tag;
    }
}
