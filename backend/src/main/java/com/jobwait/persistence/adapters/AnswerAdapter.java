package com.jobwait.persistence.adapters;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobwait.domain.Answers;
import com.jobwait.spring.utils.Utils;

public class AnswerAdapter {
    public Answers fromResultSetRow(ResultSet rs) throws AdapterException {
        try {
            JSONObject answersArrayJSONObject = new JSONObject();
            JSONArray listOfAnswersJSON = new JSONArray();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    JSONObject answerObj = new JSONObject();
                    String column_name = rsmd.getColumnLabel(i);
                    Object value = rs.getObject(i) == null ? JSONObject.NULL : rs.getObject(i);
                    answerObj.put("type", column_name);
                    answerObj.put("value", value);
                    listOfAnswersJSON.put(answerObj);
                }
            }
            answersArrayJSONObject.put("answers", listOfAnswersJSON);

            return Utils.mapper.readValue(answersArrayJSONObject.toString(), Answers.class);
        } catch (SQLException | JsonProcessingException e) {
            throw new AdapterException(e);
        }
    }
}
