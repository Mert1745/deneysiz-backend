package com.adesso.deneysiz.admin;

import com.auth0.jwt.interfaces.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.adesso.deneysiz.admin.SecurityConstants.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if (!StringUtils.hasText(token)) throw new SecurityException();

        Map<String, Claim> claims = jwtProvider.getTokenClaims(token);
        String username = claims.get("username").asString();
        String role = claims.get("role").asString();

        if (StringUtils.hasText(username)) {
            Collection<? extends GrantedAuthority> grantedAuthorities =
                    role == null ? AuthorityUtils.NO_AUTHORITIES : AuthorityUtils.createAuthorityList(role);
            UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                    new UsernamePasswordAuthenticationToken(new User(username, null, grantedAuthorities), null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
