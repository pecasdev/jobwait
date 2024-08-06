package com.jobwait.answerconversion;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;
import com.jobwait.domain.ValidEducationLevel;
import com.jobwait.domain.ValidWorkContract;
import com.jobwait.domain.ValidWorkModel;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.LocalDateAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;
import com.jobwait.spring.utils.Utils;

public class AnswerDeserializerUTest {
        @Test
        public void testAnswerDeserialize() throws Exception {
                IntegerAnswer integerAnswer = new IntegerAnswer("jobapplicationcount", 10);
                StringAnswer stringAnswer = new StringAnswer("jobtitle", "Software Developer");
                LocalDateAnswer localDateAnswer = new LocalDateAnswer("jobsearchstartdate",
                                LocalDate.now());
                ValidWorkContractAnswer validWorkContractAnswer = new ValidWorkContractAnswer("workContract",
                                ValidWorkContract.FULL_TIME);
                ValidWorkModelAnswer validWorkModelAnswer = new ValidWorkModelAnswer("workModel",
                                ValidWorkModel.HYBRID);
                ValidEducationLevelAnswer ValidEducationLevelAnswer = new ValidEducationLevelAnswer("educationLevel",
                                ValidEducationLevel.BACHELOR_DEGREE);

                List<Answer> answers = Arrays.asList(integerAnswer, stringAnswer, localDateAnswer,
                                validWorkContractAnswer,
                                validWorkModelAnswer, ValidEducationLevelAnswer);

                Answers answersWrapper = new Answers(answers);
                System.out.println(answersWrapper);

                String jsonString = Utils.mapper.writeValueAsString(answersWrapper);
                System.out.println(jsonString);

                Answers mappedAnswersWrapper = Utils.mapper.readValue(jsonString, Answers.class);
                System.out.println(mappedAnswersWrapper);

                Assertions.assertEquals(answers.size(), mappedAnswersWrapper.getAnswers().size());
                Assertions.assertIterableEquals(
                                mappedAnswersWrapper.getAnswers().stream().map(answer -> answer.toString()).toList(),
                                answers.stream().map(answer -> answer.toString()).toList());
        }
}
