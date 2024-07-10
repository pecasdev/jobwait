package com.jobwait.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;

public class InsertAnswerTables {
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
                String.format(
                        "INSERT INTO %s VALUES (?, ?) ON CONFLICT(userid) DO UPDATE SET response = EXCLUDED.response",
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
        PersistenceUtil.assertSingleRowUpdated(statement.executeUpdate());
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
