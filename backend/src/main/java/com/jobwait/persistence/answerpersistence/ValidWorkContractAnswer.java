package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidWorkContract;

public class ValidWorkContractAnswer implements Answer<ValidWorkContract> {
    private ValidWorkContract value;
    public final int sqlType = Types.OTHER;
    private String type;

    public ValidWorkContractAnswer() {
    }

    public ValidWorkContractAnswer(String type, ValidWorkContract value) {
        this.type = type;
        this.value = value;
    }

    public ValidWorkContract getValue() {
        return this.value;
    }

    public void setValue(ValidWorkContract value) {
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
        return "(type -> " + this.type + "," + "value -> " + String.valueOf(this.value) + ")";
    }
}
