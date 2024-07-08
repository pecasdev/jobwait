package com.jobwait.persistence;

import java.sql.SQLException;

public class ElementNotFoundException extends SQLException {
    public ElementNotFoundException() {
        super("An expected element could not be found");
    }
}
