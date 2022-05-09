package com.adesso.deneysiz.admin.constant;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 3600_000; // 60 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";

    private SecurityConstants() {
        throw new IllegalAccessError();
    }
}
