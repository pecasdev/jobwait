package com.jobwait.security;

public class LinkedInOAuthValidator extends OAuthValidator {
    @Override
    public void validateToken(AuthToken token) {
        if (!token.clientId().startsWith("valid")) {
            throw new RuntimeException("authtoken doesn't start with the word 'valid', therefore invalid");
        }
        // todo - query linkedin oauth endpoint and check the token
    }
}
