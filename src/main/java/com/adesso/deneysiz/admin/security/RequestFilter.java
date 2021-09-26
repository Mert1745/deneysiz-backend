package com.adesso.deneysiz.admin.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.adesso.deneysiz.admin.constant.SecurityConstants.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        AbstractAuthenticationToken abstractAuthenticationToken = null;
        try {
            abstractAuthenticationToken = new JWTAuthenticationToken(jwtProvider.decodedJWT(token));
        } catch (JWTVerificationException | IllegalArgumentException e) {
            SecurityContextHolder.clearContext();
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(abstractAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
        //TODO mkose check if clearing needed
        SecurityContextHolder.clearContext();
    }
}
