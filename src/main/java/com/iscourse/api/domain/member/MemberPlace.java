package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.course.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPlace extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public MemberPlace(Member member, Place place) {
        this.member = member;
        this.place = place;
    }
}
