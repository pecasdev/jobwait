package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.jobwait.domain.Answers;
import com.jobwait.domain.User;
import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;
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

    private class InsertAnswerTables {
        private Connection connection;
        private UUID userId;

        InsertAnswerTables(Connection connection, UUID userId) {
            this.connection = connection;
            this.userId = userId;
        };

        // this should work for now cause each answer table is just (userid, response)
        // frankly I don't think we'll ever need 2 columns for a user answer
        private PreparedStatement createStatementForTable(String tableName) throws SQLException {
            return connection.prepareStatement(
                    String.format("INSERT INTO %s VALUES (?, ?) ON CONFLICT(userid) DO UPDATE SET response = EXCLUDED.response",
                            tableName));
        }

        private interface ResponseSetter {
            void setResponse(PreparedStatement statement) throws SQLException;
        }

        private PreparedStatement createStatementAndSetFields(String tableName, ResponseSetter setResponse)
                throws SQLException {
            PreparedStatement statement = createStatementForTable(tableName);
            statement.setObject(1, userId);
            setResponse.setResponse(statement);
            return statement;
        }

        private void handleAnswerInsertion(String tableName, ResponseSetter setResponse, Object response)
                throws SQLException {
            if (response == null)
                return;
            PreparedStatement statement = createStatementAndSetFields(tableName, setResponse);
            Util.assertSingleRowUpdated(statement.executeUpdate());
        }

        private class TimestampSetter implements ResponseSetter {
            OffsetDateTime response;

            public TimestampSetter(OffsetDateTime response) {
                this.response = response;
            }

            public void setResponse(PreparedStatement statement) throws SQLException {
                statement.setTimestamp(2, Timestamp.from(response.toInstant()));
            }
        }

        public void jobAcceptDate(OffsetDateTime response) throws SQLException {
            ResponseSetter setResponse = new TimestampSetter(response);
            handleAnswerInsertion("answer_jobacceptdate", setResponse, response);
        }

        public void jobSearchStartDate(OffsetDateTime response) throws SQLException {
            ResponseSetter setResponse = new TimestampSetter(response);
            handleAnswerInsertion("answer_jobsearchstartdate", setResponse, response);
        }

        public void workModel(ValidWorkModel response) throws SQLException {
            ResponseSetter setResponse = s -> s.setString(2, response.name());
            handleAnswerInsertion("answer_workmodel", setResponse, response);
        }

        public void workContract(ValidWorkContract response) throws SQLException {
            ResponseSetter setResponse = s -> s.setString(2, response.name());
            handleAnswerInsertion("answer_workcontract", setResponse, response);
        }

        public void jobApplicationCount(Integer response) throws SQLException {
            ResponseSetter setResponse = s -> s.setInt(2, response);
            handleAnswerInsertion("answer_jobapplicationcount", setResponse, response);
        }

        public void jobTitle(String response) throws SQLException {
            ResponseSetter setResponse = s -> s.setString(2, response);
            handleAnswerInsertion("answer_jobtitle", setResponse, response);
        }

        public void yearsOfProExperience(Integer response) throws SQLException {
            ResponseSetter setResponse = s -> s.setInt(2, response);
            handleAnswerInsertion("answer_yearsofproexperience", setResponse, response);
        }

        public void educationLevel(ValidEducationLevel response) throws SQLException {
            ResponseSetter setResponse = s -> s.setString(2, response.name());
            handleAnswerInsertion("answer_educationlevel", setResponse, response);
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
