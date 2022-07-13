package com.adesso.deneysiz.admin.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.adesso.deneysiz.admin.constant.AdminUrlConstants.*;
import static com.adesso.deneysiz.integration.constant.BrandUrlConstants.BRANDS;

@EnableWebSecurity
@RequiredArgsConstructor
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {
    private final RequestFilter requestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ADMIN + LOGIN).permitAll()
                // TODO mkose remove below line remove after create first user on prod
                .antMatchers(ADMIN + SAVE_USER).permitAll()
                .antMatchers(BRANDS + "/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080", "http://localhost:3000",
                        "http://deneysiz-frontend-test.s3-website.eu-central-1.amazonaws.com",
                        "http://deneysiz-admin.s3-website.eu-central-1.amazonaws.com/");
            }
        };
    }
}
