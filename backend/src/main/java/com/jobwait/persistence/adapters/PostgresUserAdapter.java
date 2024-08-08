package com.jobwait.persistence.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jobwait.domain.User;

public class PostgresUserAdapter extends PostgresAdapter<User> {
    public User fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            String id = rs.getString("id");
            UUID uuid = UUID.fromString(id);

            String authHash = rs.getString("authhash");

            return new User(uuid, authHash);
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }

    public void statementSetPlaceholders(PreparedStatement ps, User user) throws AdapterException {
        try {
            ps.setString(1, user.authHash().toString());
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }
}
