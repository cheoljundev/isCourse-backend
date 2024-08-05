package com.iscourse.api.domain.deal;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesDetail extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "sales_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public SalesDetail(Deal deal, Member member) {
        this.deal = deal;
        this.member = member;
    }
}
