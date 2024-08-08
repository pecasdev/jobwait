package com.jobwait.persistence.adapters;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Question;
import com.jobwait.domain.Questions;
import com.jobwait.util.Enumerate;
import com.jobwait.util.Tuple;
import com.jobwait.domain.AnswerType;

public class PostgresAnswerAdapter extends PostgresAdapter<List<Answer>> {
    private Answer answerFromQuestion(Question question, ResultSet rs) throws SQLException {
        switch (question.answerType) {
            case AnswerType.STRING:
                return new Answer(question.key, AnswerType.STRING, rs.getString(question.key));

            case AnswerType.ENUM:
                return new Answer(question.key, AnswerType.ENUM, rs.getString(question.key));

            case AnswerType.INTEGER:
                Integer resultSetInteger = rs.getInt(question.key);
                Integer answer = rs.wasNull() ? null : resultSetInteger;
                return new Answer(question.key, AnswerType.INTEGER, answer);

            case AnswerType.DATE:
                Date resultSetDate = rs.getDate(question.key);
                LocalDate date = rs.wasNull() ? null : resultSetDate.toLocalDate();
                return new Answer(question.key, AnswerType.DATE, date);

            default:
                throw new RuntimeException("Answer type not defined (impossible error)");

        }
    };

    public List<Answer> fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            ArrayList<Answer> answers = new ArrayList<Answer>();
            for (Question question : Questions.known) {
                answers.add(answerFromQuestion(question, rs));
            }
            return answers;
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }

    private void setPlaceholderFromAnswer(PreparedStatement ps, Answer answer, int index) throws SQLException {
        if (answer.answerValue == null) {
            ps.setNull(index, Types.NULL);
            return;
        }

        switch (answer.getAnswerType()) {
            case AnswerType.STRING:
                ps.setString(index, answer.answerValueAsString());
                break;
            
            case AnswerType.ENUM:
                ps.setString(index, answer.answerValueAsString());
                break;

            case AnswerType.INTEGER:
                ps.setInt(index, answer.answerValueAsInteger());
                break;

            case AnswerType.DATE:
                ps.setDate(index, Date.valueOf(answer.answerValueAsDate()));
                break;

            default:
                throw new RuntimeException("Answer type not defined (impossible error)");
        }
    };

    @Override
    public void statementSetPlaceholders(PreparedStatement ps, List<Answer> answers) throws AdapterException {
        try {
            for (Tuple<Answer, Integer> answerWithIndex : Enumerate.zipWithIndex(answers)) {
                Answer answer = answerWithIndex.x;
                int index = answerWithIndex.y;
                setPlaceholderFromAnswer(ps, answer, index + 1);
            }
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }
}
