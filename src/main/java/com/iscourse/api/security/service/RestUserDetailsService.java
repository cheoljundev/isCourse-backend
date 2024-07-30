package com.iscourse.api.security.service;

import com.iscourse.api.domain.member.Member;
import com.iscourse.api.domain.member.dto.MemberContext;
import com.iscourse.api.domain.member.dto.MemberLoginDto;
import com.iscourse.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class RestUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("No user found with username : " + username);
        }

        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setId(member.getId());
        memberLoginDto.setUsername(member.getUsername());
        memberLoginDto.setPassword(member.getPassword());
        memberLoginDto.setMemberRoles(member.getMemberRoles());
        memberLoginDto.setEnabled(member.getEnabled());

        return new MemberContext(memberLoginDto);
    }
}
