package com.adesso.deneysiz.admin;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private final DecodedJWT jwt;

    public JWTAuthenticationToken(@NonNull DecodedJWT jwt) {
        super(Collections.singleton(
                Optional.ofNullable(jwt.getClaim("role").asString())
                .map(SimpleGrantedAuthority::new)
                .orElseThrow()));
        this.jwt = jwt;
    }

    @Override
    public DecodedJWT getCredentials() {
        return jwt;
    }

    @Override
    public String getPrincipal() {
        return jwt.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return jwt.getExpiresAt().after(new Date());
    }

    @Override
    public String getName() {
        return jwt.getSubject();
    }
}
