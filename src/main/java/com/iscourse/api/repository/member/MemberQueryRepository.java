package com.iscourse.api.repository.member;

import com.iscourse.api.controller.dto.member.MemberSearchCondition;
import com.iscourse.api.domain.member.*;
import com.iscourse.api.domain.member.dto.MemberAdminDetailDto;
import com.iscourse.api.domain.member.dto.MemberListDto;
import com.iscourse.api.domain.member.dto.QMemberAdminDetailDto;
import com.iscourse.api.domain.member.dto.QMemberListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.iscourse.api.domain.member.QMember.member;
import static com.iscourse.api.domain.member.QMemberRole.memberRole;

@Slf4j
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
                        genderEq(condition.getGenderType()),
                        member.enabled.isTrue()
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

    public MemberAdminDetailDto getMember(Long id) {

        List<MemberRole> memberRoles = queryFactory
                .selectFrom(memberRole)
                .where(memberRole.member.id.eq(id))
                .fetch();

        // 가장 높은 role을 선택 (가장 높은 role은 오름차순으로 결정)
        MemberRole memberRole = memberRoles.stream()
                .min(MemberRole::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원의 권한이 없습니다."));


        MemberAdminDetailDto selectMember = queryFactory
                .select(new QMemberAdminDetailDto(
                        member.id,
                        member.username,
                        member.nickname,
                        member.gender
                ))
                .from(member)
                .where(
                        member.id.eq(id),
                        member.enabled.isTrue()
                )
                .fetchOne();

        selectMember.setMemberRole(memberRole);

        return selectMember;
    }

    public Boolean validateUsername(String username) {
        log.info("username: {}", username);
        if (username == null || username.isEmpty()) {
            return false;
        }
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetchFirst() == null;
    }
}
