package com.jobwait.security;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class SecurityFaults {
    public static FaultException FailToGetTokenFault(String message) {
        return new FaultException(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_TO_GET_ACCESS_TOKEN",
                "Could not retrieve access token. %s".formatted(message));
    }

    public static FaultException GenericAuthenticationFault() {
        return new FaultException(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH_FAULT",
                "Something unexpected happened while trying to authenticate the user. Check the logs for details.");
    }

    public static FaultException FailToGetUserIDFault(String message) {
        return new FaultException(HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_TO_GET_USER_ID",
                "Could not retrieve user id. %s".formatted(message));
    }
}
