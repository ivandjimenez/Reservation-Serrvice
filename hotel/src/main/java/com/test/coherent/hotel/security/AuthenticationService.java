package com.test.coherent.hotel.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

    private static final String API_KEY = "X-API-KEY";
    @Value("${api.key}")
	private static String apiKey;
    
    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(API_KEY);
        if (apiKey == null || !apiKey.equals(apiKey)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}