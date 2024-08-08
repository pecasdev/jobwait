package com.jobwait.security;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class LinkedInOAuthValidator extends OAuthValidator {
    @Override
    public void validateToken(AuthToken token) throws FaultException {
        if (!token.clientId().startsWith("valid")) {
            throw new InvalidTokenFault();
        }
        // todo - query linkedin oauth endpoint and check the token
    }

    private class InvalidTokenFault extends FaultException {
        public InvalidTokenFault() {
            super(HttpStatus.UNAUTHORIZED, "INVALID_AUTH_TOKEN",
                    "authtoken doesn't start with the word 'valid', therefore invalid");
        }
    }
}
