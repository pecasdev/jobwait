package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Questions;
import com.jobwait.domain.User;
import com.jobwait.fault.FaultException;
import com.jobwait.persistence.adapters.PostgresAnswerAdapter;
import com.jobwait.persistence.adapters.PostgresUserAdapter;

public class PostgresController extends PersistenceController {
    private ArrayList<String> jdbcUrls = new ArrayList<String>(List.of(
            "jdbc:postgresql://database:5432/mydatabase", // inside docker-compose
            "jdbc:postgresql://localhost:5432/mydatabase" // outside docker-compose
    ));
    private String dbUser = "postgres";
    private String dbPassword = "password";

    private Connection getConnection() throws SQLException {
        try {
            String jdbcUrl = jdbcUrls.getFirst();
            return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
        } catch (SQLException | NoSuchElementException e) {
            if (!jdbcUrls.isEmpty()) {
                jdbcUrls.remove(0);
                return getConnection();
            }
            System.out.println(e);
            throw DatabaseFaults.DatabaseGetConnectionFault();
        }
    }

    @Override
    public User getUserFromAuthId(String authId) throws FaultException {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM users WHERE authhash = ?");
            statement.setString(1, authId);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet,
                    new PostgresUserAdapter());
            return PersistenceUtil.assertSingleElement(users);
        } catch (ElementNotFoundException e) {
            throw DatabaseFaults.UserNotFoundFault(authId);
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    @Override
    public List<Answer> getUserAnswersFromAuthId(User user) {
        try {
            UUID userId = user.id();
            Connection connection = getConnection();

            String answerColumnValues = String.join(", ", Questions.knownQuestionKeys);
            String statementText = "SELECT %s FROM answers WHERE userid = ?".formatted(answerColumnValues);
            PreparedStatement statement = connection.prepareStatement(statementText);

            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<List<Answer>> answers = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet,
                    new PostgresAnswerAdapter());
            return PersistenceUtil.assertSingleElement(answers);
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    /*
     * modification specifications:
     * if answerValue is null, we are deleting an answer
     * if answerValue is not null, we are inserting/updating an answer
     * if an answer is not present in a modification request, we ignore it
     */
    @Override
    public void updateUserAnswers(User user, List<Answer> answers) {
        try {
            Connection connection = getConnection();

            ArrayList<String> columnsToUpdate = new ArrayList<String>(
                    answers.stream().map(x -> x.getQuestionKey()).toList());

            ArrayList<String> valuesToUpdate = new ArrayList<String>(
                    columnsToUpdate
                            .stream()
                            .map(_ -> "?")
                            .toList());

            List<String> excludedColumnsToUpdate = columnsToUpdate
                    .stream()
                    .map(x -> "%s = EXCLUDED.%s".formatted(x, x))
                    .toList();

            // manually add userid column
            columnsToUpdate.add(0, "userid");
            valuesToUpdate.add(0, "'%s'".formatted(user.id()));

            String columnsToUpdateText = String.join(",", columnsToUpdate);
            String valuesToUpdateText = String.join(",", valuesToUpdate);
            String excludedColumnsToUpdateText = String.join(",", excludedColumnsToUpdate);

            String statementText;
            if (columnsToUpdate.size() == 1) {
                // relevant when user submits a request with zero modified answers
                // or when we are initing null answers for a new user
                statementText = """
                        INSERT INTO answers (%s)
                        VALUES (%s)
                        ON CONFLICT (userid) DO NOTHING
                        """.formatted(
                        columnsToUpdateText,
                        valuesToUpdateText);
            } else {
                statementText = """
                        INSERT INTO answers (%s)
                        VALUES (%s)
                        ON CONFLICT (userid) DO UPDATE SET %s;
                        """.formatted(
                        columnsToUpdateText,
                        valuesToUpdateText,
                        excludedColumnsToUpdateText);
            }

            PreparedStatement updateStatement = connection.prepareStatement(statementText);
            new PostgresAnswerAdapter().statementSetPlaceholders(updateStatement, answers);

            PersistenceUtil.assertSingleRowUpdated(updateStatement.executeUpdate());
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    @Override
    public User createUserFromAuthId(String authHash) {
        try {
            Connection connection = getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO users(authhash) VALUES (?) RETURNING *");
            new PostgresUserAdapter().statementSetPlaceholders(statement, new User(null, authHash));

            ResultSet resultSet = statement.executeQuery();
            List<User> users = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet, new PostgresUserAdapter());
            User user = PersistenceUtil.assertSingleElement(users);

            // init null answers for user
            this.updateUserAnswers(user, new ArrayList<Answer>());

            return user;
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    @Override
    public List<List<Answer>> getAllAnswerSets() {
        try {
            Connection connection = getConnection();

            String answerColumnValues = String.join(", ", Questions.knownQuestionKeys);
            String statementText = "SELECT %s FROM answers".formatted(answerColumnValues);
            PreparedStatement statement = connection.prepareStatement(statementText);

            ResultSet resultSet = statement.executeQuery();
            List<List<Answer>> answerSets = PersistenceUtil.resultSetRowsToAdaptedRows(resultSet,
                    new PostgresAnswerAdapter());

            return answerSets;
        } catch (SQLException e) {
            throw DatabaseFaults.GenericDatabaseFault();
        }
    }

    @Override
    public void deleteUserAndPurgeAnswers(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserAndPurgeAnswers'");
    }
}
