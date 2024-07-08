package com.jobwait.persistence.adapters;

import java.sql.SQLException;

public class AdapterException extends SQLException {
    public AdapterException(Exception e) {
        super(e.getMessage());
    }
}
