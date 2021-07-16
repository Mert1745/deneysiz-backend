package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import static com.adesso.deneysiz.admin.SecurityConstants.*;

@Component
@RequiredArgsConstructor
public class JWTProvider {

    public String createToken(User user) {
        return JWT.create()
                .withClaim("userName", user.getUserName())
                .withClaim("role", user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public Map<String, Claim> getTokenClaims(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaims();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
