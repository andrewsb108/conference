package com.spring.project.security;

/**
 * @author Andrii Barsuk
 */
public interface SecurityConstants {
    String SECRET = "andrewConference";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/users/sign-up";
    long EXPIRATION_TIME = 864_000_000; // 10 days
}
