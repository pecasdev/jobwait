package com.jobwait.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;

public class AnswersCaretaker {
    private Map<String, List<Integer>> answerTypeToMapping;

    AnswersCaretaker() {
        this.answerTypeToMapping = new HashMap<>();
        this.answerTypeToMapping.put("jobAcceptDate", Arrays.asList(2, Types.TIMESTAMP_WITH_TIMEZONE));
        this.answerTypeToMapping.put("jobSearchStartDate", Arrays.asList(3, Types.TIMESTAMP_WITH_TIMEZONE));
        this.answerTypeToMapping.put("workModel", Arrays.asList(4, Types.OTHER));
        this.answerTypeToMapping.put("workContract", Arrays.asList(5, Types.OTHER));
        this.answerTypeToMapping.put("jobApplicationCount", Arrays.asList(6, Types.INTEGER));
        this.answerTypeToMapping.put("jobTitle", Arrays.asList(7, Types.VARCHAR));
        this.answerTypeToMapping.put("yearsOfProExperience", Arrays.asList(8, Types.INTEGER));
        this.answerTypeToMapping.put("educationLevel", Arrays.asList(9, Types.OTHER));
    }

    public int answerToStatementIndex(String answerType) {
        return this.answerTypeToMapping.get(answerType).get(0);
    }

    public int answerToSqlType(String answerType) {
        return this.answerTypeToMapping.get(answerType).get(1);
    }

    public void answerToStatement(PreparedStatement statement, Answer answer) {
        Object answerValue = answer.getValue();
        Integer statementIndex = this.answerToStatementIndex(answer.getAnswerType());
        try {
            if (answerValue instanceof OffsetDateTime)
                statement.setTimestamp(statementIndex,
                        Timestamp.from(((OffsetDateTime) answerValue).toInstant()));
            else if (answerValue instanceof ValidWorkModel)
                statement.setString(statementIndex, ((ValidWorkModel) answerValue).name());
            else if (answerValue instanceof ValidWorkContract)
                statement.setString(statementIndex, ((ValidWorkContract) answerValue).name());
            else if (answerValue instanceof ValidEducationLevel)
                statement.setString(statementIndex, ((ValidEducationLevel) answerValue).name());
            else if (answerValue instanceof Integer)
                statement.setInt(statementIndex, (Integer) answerValue);
            else if (answerValue instanceof String)
                statement.setString(statementIndex, (String) answerValue);
            else if (answerValue == null)
                statement.setNull(statementIndex, this.answerToSqlType(answer.getAnswerType()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
