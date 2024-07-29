package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Answers getUserAnswersFromAuthId(String authId) {
        try {
            UUID userId = this.getUserFromAuthId(authId).id();
            Connection connection = getConnection();

            List<String> listOfAnswerTypes = Answers.listOfTypeOfAnswers;
            PreparedStatement statement = connection.prepareStatement("""
                       SELECT
                       %s
                    FROM answers WHERE userid = ?"""
                    .formatted(listOfAnswerTypes.stream().collect(Collectors.joining(", "))));

            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();

            Answers usersAnswers = new AnswerAdapter().fromResultSetRow(resultSet);

            return usersAnswers;
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(String.format("Could not find user with authId: %s", authId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Answers updateUserAnswers(User user, Answers answers) {
        try {
            Connection connection = getConnection();

            List<Answer> listOfAnswers = answers.getAnswers();
            List<String> listOfAnswerTypes = Answers.listOfTypeOfAnswers;

            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            INSERT INTO answers
                            VALUES (%s)
                            ON CONFLICT (userid) DO UPDATE
                            SET %s
                            RETURNING %s;
                                    """
                            .formatted(
                                    listOfAnswerTypes.stream().map((answerString) -> "?")
                                            .collect(Collectors.joining(", "))
                                            .concat(", ?"), // concat for userid

                                    listOfAnswerTypes.stream().map((answerString) -> answerString + " = " + "EXCLUDED."
                                            + answerString).reduce(
                                                    (answerString, answerString2) -> answerString + " , "
                                                            + answerString2)
                                            .orElseThrow(),
                                    listOfAnswerTypes.stream().map(answerString -> answerString + " AS " + answerString)
                                            .reduce((answerString, answerString2) -> answerString + " , "
                                                    + answerString2)
                                            .orElseThrow())); // getOrElse("") is wrong for sure, perhaps an OK use-case
                                                              // for raw get(), throw is safer for sure!

            updateStatement.setObject(1, user.id());

            int idx = 0;
            Map<String, Answer> updateMap = new LinkedHashMap<>(Answers.ATypeAnswerMap);

            while (idx < listOfAnswerTypes.size()) {
                if (idx < listOfAnswers.size()) {
                    updateMap.replace(listOfAnswers.get(idx).getType(), listOfAnswers.get(idx));
                }
                // will throw if key not in map (which is fair)
                updateMap.get(listOfAnswerTypes.get(idx)).setSQLStatement(updateStatement, idx + 2);
                idx += 1;
            }

            ResultSet updateResultSet = updateStatement.executeQuery();
            Answers updatedAnswers = new AnswerAdapter().fromResultSetRow(updateResultSet);

            return updatedAnswers;
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
