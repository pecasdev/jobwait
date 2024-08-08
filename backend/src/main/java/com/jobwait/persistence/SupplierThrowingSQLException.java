package com.jobwait.persistence;

import java.sql.SQLException;

@FunctionalInterface
public interface SupplierThrowingSQLException<T>  {
    T get() throws SQLException;
}
