package com.jobwait.security;

public record AuthToken(String clientId) {
    public static AuthToken fromClientId(String clientId) {
        return new AuthToken(clientId);
    }
}
