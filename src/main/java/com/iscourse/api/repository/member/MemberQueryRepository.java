package com.iscourse.api.repository.member;

import com.iscourse.api.controller.dto.member.MemberSearchCondition;
import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.dto.QTagDto;
import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.domain.member.GenderType;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberInterest;
import com.iscourse.api.domain.member.MemberRole;
import com.iscourse.api.domain.member.dto.*;
import com.iscourse.api.repository.TagRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.iscourse.api.domain.member.QMember.member;
import static com.iscourse.api.domain.member.QMemberInterest.memberInterest;
import static com.iscourse.api.domain.member.QMemberRole.memberRole;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final MemberInterestRepository memberInterestRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;


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

    public MemberEditInfoDto getMemberInfo(Long id) {
        MemberEditInfoDto memberEditInfoDto = queryFactory
                .select(new QMemberEditInfoDto(member))
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
        List<TagDto> fetchTag = queryFactory
                .select(new QTagDto(memberInterest.tag.code, memberInterest.tag.name))
                .from(memberInterest)
                .where(memberInterest.member.id.eq(id))
                .fetch();
        memberEditInfoDto.setTags(fetchTag);
        return memberEditInfoDto;
    }

    @Transactional
    public void updateMemberInfo(Long id, MemberEditInfoUpdateDto memberEditInfoUpdateDto) {
        String password = passwordEncoder.encode(memberEditInfoUpdateDto.getPassword());

        queryFactory
                .update(member)
                .set(member.nickname, memberEditInfoUpdateDto.getNickname())
                .set(member.password, password)
                .where(member.id.eq(id))
                .execute();

        queryFactory
                .delete(memberInterest)
                .where(memberInterest.member.id.eq(id))
                .execute();

        Member findMember = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        for (String code : memberEditInfoUpdateDto.getTags()) {
            Tag tag = tagRepository.findByCode(code).orElseThrow(IllegalArgumentException::new);
            MemberInterest memberInterest = new MemberInterest(findMember, tag);
            memberInterestRepository.save(memberInterest);
        }
    }
}
