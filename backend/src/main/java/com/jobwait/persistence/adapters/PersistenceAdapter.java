package com.jobwait.persistence.adapters;

import java.sql.ResultSet;

abstract public class PersistenceAdapter<A> {
    public abstract A fromResultSetRow(ResultSet rs) throws AdapterException;
}
