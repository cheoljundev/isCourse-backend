package com.iscourse.api.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberContext implements UserDetails {

    private MemberLoginDto memberLoginDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return memberLoginDto.getMemberRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                .collect(Collectors.toList());  // or use Collectors.toCollection(ArrayList::new)
    }

    @Override
    public String getPassword() {
        return memberLoginDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberLoginDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return memberLoginDto.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return memberLoginDto.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return memberLoginDto.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return memberLoginDto.getEnabled();
    }

}
