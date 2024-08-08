package com.jobwait.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jobwait.persistence.adapters.AdapterException;
import com.jobwait.persistence.adapters.PostgresAdapter;

public class PersistenceUtil {
    public static <A> List<A> resultSetRowsToAdaptedRows(ResultSet rs, PostgresAdapter<A> adapter)
            throws AdapterException {
        ArrayList<A> adaptedRows = new ArrayList<A>();

        try {
            while (rs.next()) {
                adaptedRows.add(adapter.fromResultSetRow(rs));
            }
        } catch (SQLException e) {
            throw new AdapterException(e);
        }

        return adaptedRows;
    }

    public static <A> A assertSingleElement(List<A> results) throws SQLException {
        switch (results.size()) {
            case 0:
                throw new ElementNotFoundException();
            case 1:
                return results.getFirst();
            default:
                throw new SQLException(String.format("Expected results length=1, got length=%d", results.size()));
        }
    }

    public static void assertSingleRowUpdated(int rowsUpdated) throws SQLException {
        if (rowsUpdated != 1) {
            throw new SQLException(
                    String.format("Executed INSERT expected 1 row changed, got %d row(s) changed", rowsUpdated));
        }
    }
}
