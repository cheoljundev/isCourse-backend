package com.iscourse.api.repository.member;

import com.iscourse.api.domain.course.dto.PlaceListDto;
import com.iscourse.api.domain.course.dto.QPlaceListDto;
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

    public Page<PlaceListDto> getMemberPlaceList(Pageable pageable) {
        JPAQuery<PlaceListDto> query = queryFactory
                .select(new QPlaceListDto(
                        memberPlace.place.id,
                        memberPlace.place.name,
                        memberPlace.place.image
                ))
                .from(memberPlace)
                .where(memberPlace.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<PlaceListDto> contents = query.fetch();
        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
