package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.dto.PlaceListDto;
import com.iscourse.api.domain.course.dto.QPlaceListDto;
import com.iscourse.api.domain.member.MemberPlace;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.member.QMemberPlace.*;

@Repository
@RequiredArgsConstructor
public class MemberPlaceQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<PlaceListDto> getMemberPlaceList(Long memberId, Pageable pageable) {
        List<PlaceListDto> contents = queryFactory
                .select(new QPlaceListDto(
                        memberPlace.place.id,
                        memberPlace.place.name,
                        memberPlace.place.image
                ))
                .from(memberPlace)
                .where(memberPlace.enabled.eq(true), memberPlace.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<MemberPlace> countQuery = queryFactory
                .selectFrom(memberPlace)
                .where(memberPlace.enabled.eq(true), memberPlace.member.id.eq(memberId))
                .fetch();
        int total = countQuery.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
