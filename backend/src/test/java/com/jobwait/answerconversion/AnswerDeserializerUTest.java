package com.jobwait.answerconversion;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;

public class AnswerDeserializerUTest {
    @Test
    public void testAnswerDeserialize() throws Exception {
        ObjectMapper mapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
                .build();

        IntegerAnswer intAnswer = new IntegerAnswer("jobapplicationcount", 10);
        StringAnswer strAnswer = new StringAnswer("jobtitle", "Software Developer");
        List<Answer> answers = new ArrayList<>();
        answers.add(intAnswer);
        answers.add(strAnswer);

        // Integer test = answers.get(0).getValue();

        Answers answersWrapper = new Answers(answers);
        System.out.println(answersWrapper);

        String jsonString = mapper.writeValueAsString(answersWrapper);
        System.out.println(jsonString);

        Answers mappedAnswersWrapper = mapper.readValue(jsonString, Answers.class);
        System.out.println(mappedAnswersWrapper);

        Assertions.assertEquals(2, mappedAnswersWrapper.getAnswers().size());
        Assertions.assertIterableEquals(
                mappedAnswersWrapper.getAnswers().stream().map(answer -> answer.toString()).toList(),
                answers.stream().map(answer -> answer.toString()).toList());
    }
}
