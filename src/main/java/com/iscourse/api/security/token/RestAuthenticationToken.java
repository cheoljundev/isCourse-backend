package com.iscourse.api.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RestAuthenticationToken extends AbstractAuthenticationToken {

    private final Object  principle;
    private final Object credentials;

    public RestAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principle, Object credentials) {
        super(authorities);
        this.principle = principle;
        this.credentials = credentials;
        setAuthenticated(true);
    }
    public RestAuthenticationToken(Object principle, Object credentials) {
        super(null);
        this.principle = principle;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
