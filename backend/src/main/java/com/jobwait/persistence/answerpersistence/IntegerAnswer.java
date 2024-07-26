package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.jobwait.domain.Answer;

public class IntegerAnswer implements Answer<Integer> {
    private Integer value;
    public final int sqlType = Types.INTEGER;
    private String type;

    public IntegerAnswer() {
    }

    public IntegerAnswer(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
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
            statement.setInt(statementIndex, this.getValue());
        }
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + "," + "value -> " + String.valueOf(this.value) + ")";
    }
}
