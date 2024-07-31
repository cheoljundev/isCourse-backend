package com.iscourse.api.repository.member;

import com.iscourse.api.domain.member.MemberCourse;
import com.iscourse.api.domain.member.QMemberCourse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.member.QMemberCourse.*;

@Repository
@RequiredArgsConstructor
public class MemberCourseQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MemberCourse> getMemberCourseList(Pageable pageable) {
        JPAQuery<MemberCourse> query = queryFactory
                .selectFrom(memberCourse)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<MemberCourse> contents = query.fetch();
        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }
}
