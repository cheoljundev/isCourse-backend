package com.iscourse.api.repository.member;

import com.iscourse.api.controller.dto.member.MemberSearchCondition;
import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.dto.MemberListDto;
import com.iscourse.api.domain.member.dto.QMemberListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MemberListDto> getMemberList(MemberSearchCondition condition, Pageable pageable) {
        JPAQuery<MemberListDto> query = queryFactory
                .select(new QMemberListDto(
                        member.id,
                        member.username,
                        member.nickname
                ))
                .from(member)
                .where(
                        usernameEq(condition.getUsername()),
                        nicknameEq(condition.getNickname()),
                        genderEq(condition.getGenderType())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        List<MemberListDto> contents = query.fetch();
        int total = contents.size();
        return PageableExecutionUtils.getPage(contents, pageable, () -> total);
    }

    private BooleanExpression usernameEq(String username) {
        return username == null ? null : member.username.eq(username);
    }

    private BooleanExpression nicknameEq(String nickname) {
        return nickname == null ? null : member.nickname.eq(nickname);
    }

    private BooleanExpression genderEq(GenderType genderType) {
        return genderType == null ? null : member.gender.eq(genderType);
    }
}
