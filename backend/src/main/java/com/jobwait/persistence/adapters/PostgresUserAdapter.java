package com.jobwait.persistence.adapters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jobwait.domain.User;

public class PostgresUserAdapter extends PersistenceAdapter<User>{
    public User fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            String id = rs.getString("id");
            UUID uuid = UUID.fromString(id);
            return new User(uuid);
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }
}
