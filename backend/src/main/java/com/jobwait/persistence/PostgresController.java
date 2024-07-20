package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.persistence.adapters.PostgresUserAdapter;
import com.jobwait.persistence.adapters.AnswerAdapter;

public class PostgresController extends PersistenceController {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/mydatabase";
    private String dbUser = "postgres";
    private String dbPassword = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    }

    @Override
    public User getUserFromAuthId(String authId) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE authhash = ?");
            statement.setString(1, authId);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet, new PostgresUserAdapter());
            return PersistenceUtil.assertSingleElement(users);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(String.format("Could not find user with authId: %s", authId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<Answer>> getUserAnswersFromAuthId(String authId) {
        try {
            UUID userId = this.getUserFromAuthId(authId).id();

            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                       SELECT
                       answer_jobacceptdate, answer_jobsearchstartdate,
                       answer_workmodel, answer_workcontract,
                       answer_jobapplicationcount, answer_jobtitle,
                       answer_yearsofproexperience, answer_educationlevel
                    FROM answers WHERE userid = ?""");

            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();

            Map<String, List<Answer>> returnMap = new HashMap<>();
            List<Answer> usersAnswers = new AnswerAdapter().fromResultSetRow(resultSet);
            returnMap.put(authId, usersAnswers);

            return returnMap;
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(String.format("Could not find user with authId: %s", authId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<Answer>> updateUserAnswers(User user, Answers answers) {
        try {
            System.out.format("answers given: %s", answers.toString());
            Connection connection = getConnection();

            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            INSERT INTO answers
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                            ON CONFLICT (userid) DO UPDATE
                            SET
                            answer_jobacceptdate = EXCLUDED.answer_jobacceptdate , answer_jobsearchstartdate = EXCLUDED.answer_jobsearchstartdate,
                            answer_workmodel = EXCLUDED.answer_workmodel , answer_workcontract = EXCLUDED.answer_workcontract ,
                            answer_jobapplicationcount = EXCLUDED.answer_jobapplicationcount, answer_jobtitle = EXCLUDED.answer_jobtitle ,
                            answer_yearsofproexperience = EXCLUDED.answer_yearsofproexperience , answer_educationlevel = EXCLUDED.answer_educationlevel
                            RETURNING answer_jobacceptdate, answer_jobsearchstartdate,
                            answer_workmodel, answer_workcontract,
                            answer_jobapplicationcount, answer_jobtitle,
                            answer_yearsofproexperience, answer_educationlevel;
                                    """);

            updateStatement.setObject(1, user.id());

            AnswersCaretaker answersCaretaker = new AnswersCaretaker();

            answers.listOfAnswers()
                    .forEach(answer -> answersCaretaker.answerToStatement(updateStatement, answer));

            ResultSet updateResultSet = updateStatement.executeQuery();
            List<Answer> updatedAnswers = new AnswerAdapter().fromResultSetRow(updateResultSet);

            Map<String, List<Answer>> returnMap = new HashMap<>();
            returnMap.put(user.id().toString(), updatedAnswers);
            return returnMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User createUserFromAuthId(String authId) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO users(authhash) VALUES (?) RETURNING *");
            statement.setString(1, authId);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet, new PostgresUserAdapter());
            return PersistenceUtil.assertSingleElement(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserAndPurgeAnswers(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserAndPurgeAnswers'");
    }
}
