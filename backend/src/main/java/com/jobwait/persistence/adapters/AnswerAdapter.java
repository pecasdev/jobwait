package com.jobwait.persistence.adapters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jobwait.domain.Answers;
import com.jobwait.domain.User;

public class AnswerAdapter extends PersistenceAdapter<Answers> {
    public Answers fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            String id = rs.getString("id");
            UUID uuid = UUID.fromString(id);
            return new Answers(...);
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }
}
