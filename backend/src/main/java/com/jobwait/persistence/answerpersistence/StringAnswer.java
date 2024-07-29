package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.jobwait.domain.Answer;

public class StringAnswer implements Answer<String> {
    private String value;
    private final int sqlType = Types.VARCHAR;
    private String type;

    public StringAnswer() {

    }

    public StringAnswer(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSQLStatement(PreparedStatement statement, Integer statementIndex) throws SQLException {
        if (this.getValue() == null) {
            statement.setNull(statementIndex, this.sqlType);
        } else {
            statement.setString(statementIndex, this.getValue());
        }
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + "," + "value -> " + this.value + ")";
    }
}
