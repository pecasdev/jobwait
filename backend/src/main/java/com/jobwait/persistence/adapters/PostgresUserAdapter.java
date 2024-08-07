package com.jobwait.persistence.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jobwait.domain.User;
import com.jobwait.fault.FaultException;
import com.jobwait.persistence.DatabaseFaults;

public class PostgresUserAdapter extends PostgresAdapter<User> {
    public User fromResultSetRow(ResultSet rs) throws FaultException {
        try {
            String id = rs.getString("id");
            UUID uuid = UUID.fromString(id);

            String authHash = rs.getString("authhash");

            return new User(uuid, authHash);
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    public void statementSetPlaceholders(PreparedStatement ps, User user) throws FaultException {
        try {
            ps.setString(1, user.authHash().toString());
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }
}
