package com.jobwait.persistence.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jobwait.fault.FaultException;

abstract public class PostgresAdapter<A> {
    public abstract A fromResultSetRow(ResultSet rs) throws FaultException;

    public abstract void statementSetPlaceholders(PreparedStatement ps, A toPersist) throws FaultException;
}
