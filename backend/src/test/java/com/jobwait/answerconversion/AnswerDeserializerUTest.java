package com.jobwait.answerconversion;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;

public class AnswerDeserializerUTest {
    @Test
    public void testAnswerDeserialize() throws Exception {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                true);

        IntegerAnswer intAnswer = new IntegerAnswer("jobApplicationCount", 10);
        StringAnswer strAnswer = new StringAnswer("jobTitle", "Software Developer");
        List<Answer> answers = new ArrayList<>();
        answers.add(intAnswer);
        answers.add(strAnswer);

        // Integer test = answers.get(0).getValue();

        Answers answersWrapper = new Answers();
        answersWrapper.setAnswers(answers);
        System.out.println(answersWrapper);

        String jsonString = mapper.writeValueAsString(answersWrapper);
        System.out.println(jsonString);

        Answers mappedAnswersWrapper = mapper.readValue(jsonString, Answers.class);
        System.out.println(mappedAnswersWrapper);

        Assertions.assertEquals(2, mappedAnswersWrapper.getListOfAnswers().size());
        Assertions.assertIterableEquals(
                mappedAnswersWrapper.getListOfAnswers().stream().map(answer -> answer.toString()).toList(),
                answers.stream().map(answer -> answer.toString()).toList());
    }
}
