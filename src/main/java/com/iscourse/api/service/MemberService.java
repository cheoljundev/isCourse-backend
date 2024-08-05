package com.iscourse.api.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TagRepository tagRepository;
    private final MemberInterestRepository memberInterestRepository;

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
}
