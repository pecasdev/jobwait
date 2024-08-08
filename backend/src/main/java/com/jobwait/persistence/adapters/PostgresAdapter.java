package com.jobwait.persistence.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

abstract public class PostgresAdapter<A> {
    public abstract A fromResultSetRow(ResultSet rs) throws AdapterException;

    public abstract void statementSetPlaceholders(PreparedStatement ps, A toPersist) throws AdapterException;
}
