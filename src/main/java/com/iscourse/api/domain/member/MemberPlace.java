package com.iscourse.api.domain.member;

import com.iscourse.api.domain.BaseEntity;
import com.iscourse.api.domain.course.Place;
import jakarta.persistence.*;

@Entity
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

}
