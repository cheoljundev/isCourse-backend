package com.iscourse.api.repository.member;

import com.iscourse.api.domain.member.MemberCourse;
import com.iscourse.api.domain.member.MemberPlace;
import com.iscourse.api.domain.member.QMemberPlace;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.member.QMemberCourse.memberCourse;
import static com.iscourse.api.domain.member.QMemberPlace.*;

@Repository
@RequiredArgsConstructor
public class MemberPlaceQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MemberPlace> getMemberPlaceList(Pageable pageable) {
        JPAQuery<MemberPlace> query = queryFactory
                .selectFrom(memberPlace)
                .where(memberPlace.enabled.eq(true))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<MemberPlace> contents = query.fetch();
        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
