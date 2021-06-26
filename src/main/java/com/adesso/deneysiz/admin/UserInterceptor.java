package com.adesso.deneysiz.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.adesso.deneysiz.admin.SecurityConstants.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    private final JWTProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getRequestURI().equals("/admin/login")) {
            return true;
        }
        String token = request.getHeader(AUTHORIZATION);
        return jwtProvider.isTokenValid(token);
    }
}
