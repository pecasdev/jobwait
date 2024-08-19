package com.jobwait.security.exchange.exchangefaults;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class FailToGetUserIDFault extends FaultException {
    public FailToGetUserIDFault(String message) {
        super(HttpStatus.UNAUTHORIZED, "FAILED_TO_GET_USER_ID",
                "Could not retrieve user id. %s".formatted(message));
    }
}
