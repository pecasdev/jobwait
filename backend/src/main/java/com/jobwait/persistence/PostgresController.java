package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.persistence.adapters.PostgresUserAdapter;

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
            List<User> users = Util.resultSetRowsToAdaptedRows(resultSet, new PostgresUserAdapter());
            return Util.assertSingleElement(users);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(String.format("Could not find user with authId: %s", authId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUserAnswers(User user, Answers answers) {
        try {
            System.out.format("answers given: %s", answers.toString());
            Connection connection = getConnection();
            connection.setAutoCommit(false);

            InsertAnswerTables insertAnswerTables = new InsertAnswerTables(connection, user.id());
            insertAnswerTables.jobAcceptDate(answers.jobAcceptDate());
            insertAnswerTables.jobSearchStartDate(answers.jobSearchStartDate());
            insertAnswerTables.workModel(answers.workModel());
            insertAnswerTables.workContract(answers.workContract());
            insertAnswerTables.jobApplicationCount(answers.jobApplicationCount());
            insertAnswerTables.jobTitle(answers.jobTitle());
            insertAnswerTables.yearsOfProExperience(answers.yearsOfProExperience());
            insertAnswerTables.educationLevel(answers.educationLevel());

            connection.commit();
            return user;
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
            List<User> users = Util.resultSetRowsToAdaptedRows(resultSet, new PostgresUserAdapter());
            return Util.assertSingleElement(users);
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
