package com.jobwait.answerconversion;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;
import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.OffsetDateTimeAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;

public class AnswerDeserializerUTest {
    @Test
    public void testAnswerDeserialize() throws Exception {
        ObjectMapper mapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
                .build().registerModule(new JavaTimeModule());

        IntegerAnswer integerAnswer = new IntegerAnswer("jobapplicationcount", 10);
        StringAnswer stringAnswer = new StringAnswer("jobtitle", "Software Developer");
        OffsetDateTimeAnswer offsetDateTimeAnswer = new OffsetDateTimeAnswer("jobsearchstartdate",
                OffsetDateTime.now());
        ValidWorkContractAnswer validWorkContractAnswer = new ValidWorkContractAnswer("workContract",
                ValidWorkContract.FULL_TIME);
        ValidWorkModelAnswer validWorkModelAnswer = new ValidWorkModelAnswer("workModel", ValidWorkModel.HYBRID);
        ValidEducationLevelAnswer ValidEducationLevelAnswer = new ValidEducationLevelAnswer("educationLevel",
                ValidEducationLevel.BACHELOR_DEGREE);

        List<Answer> answers = Arrays.asList(integerAnswer, stringAnswer, offsetDateTimeAnswer, validWorkContractAnswer,
                validWorkModelAnswer, ValidEducationLevelAnswer);

        Answers answersWrapper = new Answers(answers);
        System.out.println(answersWrapper);

        String jsonString = mapper.writeValueAsString(answersWrapper);
        System.out.println(jsonString);

        Answers mappedAnswersWrapper = mapper.readValue(jsonString, Answers.class);
        System.out.println(mappedAnswersWrapper);

        Assertions.assertEquals(answers.size(), mappedAnswersWrapper.getAnswers().size());
        Assertions.assertIterableEquals(
                mappedAnswersWrapper.getAnswers().stream().map(answer -> answer.toString()).toList(),
                answers.stream().map(answer -> answer.toString()).toList());
    }
}
