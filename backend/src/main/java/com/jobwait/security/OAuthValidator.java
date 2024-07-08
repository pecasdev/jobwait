package com.jobwait.security;

abstract public class OAuthValidator {
    abstract public void validateToken(AuthToken token);
}
