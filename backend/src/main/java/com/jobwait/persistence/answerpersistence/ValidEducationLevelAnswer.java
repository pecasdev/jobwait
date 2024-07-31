package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidEducationLevel;

public class ValidEducationLevelAnswer implements Answer<ValidEducationLevel> {
    private ValidEducationLevel value;
    private final int sqlType = Types.OTHER;
    private String type;

    public ValidEducationLevelAnswer() {
    }

    public ValidEducationLevelAnswer(String type, ValidEducationLevel value) {
        this.type = type;
        this.value = value;
    }

    public ValidEducationLevel getValue() {
        return this.value;
    }

    public void setValue(ValidEducationLevel value) {
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
            statement.setString(statementIndex, this.getValue().name());
        }
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + " , " + "value -> " + "[" + String.valueOf(this.value.getClass()) + "] "
                + String.valueOf(this.value) + ")";
    }
}
