package com.jobwait.persistence;

import org.springframework.http.HttpStatus;

import com.jobwait.fault.FaultException;

public class DatabaseFaults {
    public static FaultException UserNotFoundFault(String authId) {
        return new FaultException(HttpStatus.NOT_FOUND, "USER_NOT_FOUND",
                "Could not find user with authId: %s".formatted(authId));
    }

    public static FaultException GenericDatabaseFault() {
        return new FaultException(HttpStatus.INTERNAL_SERVER_ERROR, "DATABASE_FAULT",
                "Something unexpected happened in the database, check the logs for details");
    }

    public static FaultException DatabaseGetConnectionFault() {
        return new FaultException(HttpStatus.INTERNAL_SERVER_ERROR, "DATABASE_INIT_FAULT",
                "Could not get connection to database. Is it running?");
    }
}
