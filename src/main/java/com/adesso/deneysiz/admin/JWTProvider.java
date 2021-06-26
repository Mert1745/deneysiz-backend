package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.adesso.deneysiz.admin.SecurityConstants.*;

@Component
@RequiredArgsConstructor
public class JWTProvider {
    private final UserRepository userRepository;

    public String createToken(UserDTO userDTO) {
        return JWT.create()
                .withSubject(userDTO.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public boolean isTokenValid(String token) {
        String userName = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();

        return userRepository.getByUserName(userName) != null;
    }
}
