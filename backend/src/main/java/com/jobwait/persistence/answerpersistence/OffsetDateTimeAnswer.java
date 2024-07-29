package com.jobwait.persistence.answerpersistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.OffsetDateTime;

import com.jobwait.domain.Answer;

public class OffsetDateTimeAnswer implements Answer<OffsetDateTime> {
    private OffsetDateTime value;
    private final int sqlType = Types.TIMESTAMP_WITH_TIMEZONE;
    private String type;

    public OffsetDateTimeAnswer() {
    }

    public OffsetDateTimeAnswer(String type, OffsetDateTime value) {
        this.type = type;
        this.value = value;
    }

    public OffsetDateTime getValue() {
        return this.value;
    }

    public void setValue(OffsetDateTime value) {
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
            statement.setTimestamp(statementIndex,
                    Timestamp.from((this.getValue()).toInstant()));
        }
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + "," + "value -> " + String.valueOf(this.value) + ")";
    }
}
