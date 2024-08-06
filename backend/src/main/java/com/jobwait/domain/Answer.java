package com.jobwait.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.LocalDateAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
                @JsonSubTypes.Type(value = StringAnswer.class, name = "jobTitle"),
                @JsonSubTypes.Type(value = IntegerAnswer.class, names = { "yearsOfProExperience",
                                "jobApplicationCount" }),
                @JsonSubTypes.Type(value = ValidEducationLevelAnswer.class, name = "educationLevel"),
                @JsonSubTypes.Type(value = ValidWorkModelAnswer.class, name = "workModel"),
                @JsonSubTypes.Type(value = ValidWorkContractAnswer.class, name = "workContract"),
                @JsonSubTypes.Type(value = LocalDateAnswer.class, names = { "jobAcceptDate",
                                "jobSearchStartDate" }),
})
public interface Answer<T> {
        public T getValue();

        public void setValue(T value);

        public String getType();

        public void setType(String type);

        public void setSQLStatement(PreparedStatement statement, Integer statementIndex) throws SQLException;
}
