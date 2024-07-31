package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

    private final String answerValueQuestionString = Answers.listOfTypeOfAnswers.stream().map((answerString) -> "?")
            .collect(Collectors.joining(", "))
            .concat(", ?");// concat for userid

    private final String answerValueExcludedUpdateString = Answers.listOfTypeOfAnswers.stream()
            .map((answerString) -> answerString + " = " + "EXCLUDED."
                    + answerString)
            .reduce(
                    (answerString, answerString2) -> answerString + " , "
                            + answerString2)
            .orElseThrow();

    private final String answerValueReturnString = Answers.listOfTypeOfAnswers.stream()
            .map(answerString -> answerString + " AS " + answerString)
            .reduce((answerString, answerString2) -> answerString + " , "
                    + answerString2)
            .orElseThrow();

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
    public Answers getUserAnswersFromAuthId(User user) {
        try {
            UUID userId = user.id();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns an Answers object that is the result of comparing persisted and newly
     * submitted Answers. The returned Answers object should have the least
     * null/empty
     * answer values between the two.
     * <p>
     * This method always returns an Answers object with a List< Answer > that
     * is
     * full. For example, the List will always have all the possible answer
     * types.
     *
     * @param persistedAnswers the currently persisted answers for the user
     * @param submittedAnswers the newly submitted answers for the user
     * @return Answers object with the least number of null/empty answer values.
     * @see Answers
     */
    private Answers mergeAnswers(Answers persistedAnswers, Answers submittedAnswers) {
        Map<String, Answer> mergerMap = persistedAnswers.getAnswers().stream()
                .collect(Collectors.toMap(Answer::getType, answer -> answer));

        submittedAnswers.getAnswers().forEach(answer -> {
            String answerType = answer.getType().toLowerCase();
            if (mergerMap.containsKey(answerType) && answer.getValue() != null) {
                mergerMap.replace(answerType, answer);
            }
        });

        return new Answers(mergerMap.values().stream().toList());
    }

    @Override
    public Answers updateUserAnswers(User user, Answers answers) {
        try {
            Connection connection = getConnection();

            List<Answer> listOfAnswers = this.mergeAnswers(this.getUserAnswersFromAuthId(user),
                    answers).getAnswers();

            String answerValueColumnString = listOfAnswers.stream().map(Answer::getType)
                    .collect(Collectors.joining(", "))
                    .concat(", userid");// concat for userid

            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            INSERT INTO answers (%s)
                            VALUES (%s)
                            ON CONFLICT (userid) DO UPDATE
                            SET %s
                            RETURNING %s;
                                    """
                            .formatted(
                                    answerValueColumnString,
                                    answerValueQuestionString,
                                    answerValueExcludedUpdateString,
                                    answerValueReturnString));

            updateStatement.setObject(listOfAnswers.size() + 1, user.id());

            for (int idx = 0; idx < listOfAnswers.size(); idx++) {
                listOfAnswers.get(idx).setSQLStatement(updateStatement, idx + 1);
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
