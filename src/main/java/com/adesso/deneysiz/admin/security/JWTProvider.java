package com.adesso.deneysiz.admin.security;

import com.adesso.deneysiz.admin.constant.Claims;
import com.adesso.deneysiz.admin.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.adesso.deneysiz.admin.constant.SecurityConstants.*;

@Component
@RequiredArgsConstructor
public class JWTProvider {
    public String createToken(User user) {
        return JWT.create()
                .withClaim(Claims.USER_NAME, user.getUserName())
                .withClaim(Claims.ROLE, user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public DecodedJWT decodedJWT(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));
    }
}
