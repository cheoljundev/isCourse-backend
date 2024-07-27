package com.iscourse.api.service;

import com.iscourse.api.domain.Tag;
import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.MemberInterest;
import com.iscourse.api.domain.member.MemberRole;
import com.iscourse.api.domain.member.MemberRoleType;
import com.iscourse.api.dto.member.SignUpMemberDto;
import com.iscourse.api.repository.TagRepository;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagRepository;

    public void signup(SignUpMemberDto sIgnUpMemberDto) {
        Member member = new Member(sIgnUpMemberDto.getUsername(), passwordEncoder.encode(sIgnUpMemberDto.getPassword()), sIgnUpMemberDto.getGender(), sIgnUpMemberDto.getNickname());
        member.addRole(MemberRole.create(MemberRoleType.ROLE_USER));
        for (Long interest : sIgnUpMemberDto.getInterests()) {
            Tag tag = tagRepository.findById(interest).orElseThrow(IllegalArgumentException::new);
            member.addInterest(MemberInterest.create(tag));
        }
        memberRepository.save(member);
    }
}
