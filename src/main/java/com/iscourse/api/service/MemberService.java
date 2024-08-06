package com.iscourse.api.service;

import com.iscourse.api.controller.dto.member.UpdateMemberRole;
import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.dto.TagDto;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberInterest;
import com.iscourse.api.domain.member.MemberRole;
import com.iscourse.api.domain.member.MemberRoleType;
import com.iscourse.api.domain.member.dto.SignUpMemberDto;
import com.iscourse.api.repository.TagRepository;
import com.iscourse.api.repository.member.MemberInterestRepository;
import com.iscourse.api.repository.member.MemberRepository;
import com.iscourse.api.repository.member.MemberRoleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {
    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagRepository;
    private final MemberInterestRepository memberInterestRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Transactional
    public void signup(SignUpMemberDto sIgnUpMemberDto) {
        Member member = new Member(sIgnUpMemberDto.getUsername(), passwordEncoder.encode(sIgnUpMemberDto.getPassword()), sIgnUpMemberDto.getGender(), sIgnUpMemberDto.getNickname());
        member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
        memberRepository.save(member);
        for (String interest : sIgnUpMemberDto.getInterests()) {
            Tag tag = tagRepository.findByCode(interest).orElseThrow(IllegalArgumentException::new);
            MemberInterest memberInterest = new MemberInterest(member, tag);
            memberInterestRepository.save(memberInterest);
        }
    }

    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tag -> new TagDto(tag.getCode(), tag.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateRole(Long id, UpdateMemberRole updateMemberRole) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        switch (updateMemberRole.getMemberRoleType()) {
            case ROLE_USER:
                memberRoleRepository.deleteAll(member.getMemberRoles());
                member.getMemberRoles().clear();
                member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
                log.info("getMemberRoles : {}", member.getMemberRoles());
                break;
            case ROLE_MANAGER:
                memberRoleRepository.deleteAll(member.getMemberRoles());
                member.getMemberRoles().clear();
                member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
                member.addRole(MemberRole.create(MemberRoleType.ROLE_MANAGER));
                log.info("getMemberRoles : {}", member.getMemberRoles());
                break;
            case ROLE_ADMIN:
                memberRoleRepository.deleteAll(member.getMemberRoles());
                member.getMemberRoles().clear();
                member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
                member.addRole(MemberRole.create(MemberRoleType.ROLE_MANAGER));
                member.addRole(MemberRole.create(MemberRoleType.ROLE_ADMIN));
                log.info("getMemberRoles : {}", member.getMemberRoles());
                break;
        }
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.findById(id).ifPresent(Member::delete);
    }
}
