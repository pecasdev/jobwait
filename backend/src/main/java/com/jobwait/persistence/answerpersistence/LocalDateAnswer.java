package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import com.jobwait.domain.Answer;

public class LocalDateAnswer implements Answer<LocalDate> {
    private LocalDate value;
    private final int sqlType = Types.DATE;
    private String type;

    public LocalDateAnswer() {
    }

    public LocalDateAnswer(String type, LocalDate value) {
        this.type = type;
        this.value = value;
    }

    public LocalDate getValue() {
        return this.value;
    }

    public void setValue(LocalDate value) {
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
            statement.setObject(statementIndex,
                    this.getValue());
        }
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + " , " + "value -> " + "[" + String.valueOf(this.value.getClass()) + "] "
                + String.valueOf(this.value) + ")";
    }
}
