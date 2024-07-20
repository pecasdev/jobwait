package com.jobwait.persistence.adapters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;

public class AnswerAdapter {
    public List<Answer> fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            List<Answer> listOfAnswers = new ArrayList<>();
            while (rs.next()) {
                if (rs.getDate("answer_jobacceptdate") != null) {
                    listOfAnswers.add(new Answer<OffsetDateTime>(
                            rs.getDate("answer_jobacceptdate").toInstant().atOffset(ZoneOffset.UTC)));
                }

                if (rs.getDate("answer_jobsearchstartdate") != null) {
                    listOfAnswers.add(new Answer<OffsetDateTime>(
                            rs.getDate("answer_jobacceptdate").toInstant().atOffset(ZoneOffset.UTC)));
                }

                if (rs.getString("answer_workmodel") != null) {
                    listOfAnswers.add(new Answer<ValidWorkModel>(
                            ValidWorkModel.valueOf(rs.getString("answer_workmodel"))));
                }

                if (rs.getString("answer_workcontract") != null) {
                    listOfAnswers.add(new Answer<ValidWorkContract>(
                            ValidWorkContract.valueOf(rs.getString("answer_workcontract"))));
                }

                if (rs.getInt("answer_jobapplicationcount") != 0) {
                    listOfAnswers.add(new Answer<Integer>(
                            Integer.valueOf(rs.getInt("answer_jobapplicationcount"))));
                }

                if (rs.getString("answer_educationlevel") != null) {
                    listOfAnswers.add(new Answer<ValidEducationLevel>(
                            ValidEducationLevel.valueOf(rs.getString("answer_educationlevel"))));
                }

                if (rs.getString("answer_jobtitle") != null) {
                    listOfAnswers.add(new Answer<String>(
                            rs.getString("answer_jobtitle")));
                }

                if (rs.getString("answer_jobtitle") != null) {
                    listOfAnswers.add(new Answer<String>(
                            rs.getString("answer_jobtitle")));
                }

                if (rs.getInt("answer_yearsofproexperience") != 0) {
                    listOfAnswers.add(new Answer<Integer>(
                            Integer.valueOf(rs.getInt("answer_yearsofproexperience"))));
                }
            }
            return listOfAnswers;
        } catch (SQLException e) {
            throw new AdapterException(e);
        }
    }
}
