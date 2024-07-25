package com.jobwait.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jobwait.answerconversion.AnswerDeserializer;
import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.OffsetDateTimeAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;

import static java.util.Map.entry;

/*
 * Q: why are we using Integer instead of int?
 * A: jackson, the deserializer spring uses for the JSON request body, sets any
 * missing parameters as null when creating an Answers record. Primitives in
 * java (int, double, float) cannot be null, so they get set to zero by jackson,
 * which is a problem. Using the class Integer fixes this issue.
 */
public class Answers {
        public static final Map<String, Answer> ATypeAnswerMap = new HashMap<String, Answer>(
                        Map.ofEntries(
                                        entry("jobAcceptDate", new OffsetDateTimeAnswer("jobAcceptDate", null)),
                                        entry("jobSearchStartDate",
                                                        new OffsetDateTimeAnswer("jobSearchStartDate", null)),
                                        entry("workModel", new ValidWorkModelAnswer("workModel", null)),
                                        entry("workContract", new ValidWorkContractAnswer("workContract", null)),
                                        entry("jobApplicationCount", new IntegerAnswer("jobApplicationCount", null)),
                                        entry("jobTitle", new StringAnswer("jobTitle", null)),
                                        entry("yearsOfProExperience", new IntegerAnswer("yearsOfProExperience", null)),
                                        entry("educationLevel",
                                                        new ValidEducationLevelAnswer("educationLevel", null))));

        public static final List<String> listOfTypeOfAnswers = ATypeAnswerMap.keySet().stream().toList();
}
// public static final Map<String, Answer> ATypeAnswerMap =
// Map.ofEntries(
// entry("answer_jobacceptdate", new Answer<OffsetDateTime>()),
// entry("answer_jobsearchstartdate", new Answer<OffsetDateTime>()),
// entry("answer_workmodel", new Answer<ValidWorkModel>()),
// entry("answer_workcontract", new Answer<ValidWorkContract>()),
// entry("answer_jobapplicationcount", new Answer<Integer>()),
// entry("answer_jobtitle", new Answer<String>()),
// entry("answer_yearsofproexperience", new Answer<Integer>()),
// entry("answer_educationlevel", new Answer<ValidEducationLevel>()));

// public static final List<String> listOfAnswerTypes = new
// ArrayList<String>(ATypeAnswerMap.keySet());

// public Answers(OffsetDateTime jobAcceptDate,
// OffsetDateTime jobSearchStartDate,
// ValidWorkModel workModel,
// ValidWorkContract workContract,
// Integer jobApplicationCount,
// String jobTitle,
// Integer yearsOfProExperience,
// ValidEducationLevel educationLevel) {
// this.jobAcceptDate = jobAcceptDate;
// this.jobSearchStartDate = jobSearchStartDate;
// this.workModel = workModel;
// this.workContract = workContract;
// this.jobApplicationCount = jobApplicationCount;
// this.jobTitle = jobTitle;
// this.yearsOfProExperience = yearsOfProExperience;
// this.educationLevel = educationLevel;
// }

// public Answers(List<Answer> listOfAnswers) {
// this(Answers.answerToAnswers(listOfAnswers));
// }

// private static Answers listOfAnswerToAnswers(List<Answer> listOfAnswers){
// Map<String, Answer> answerMap = new HashMap<String,
// Answer>(Answers.ATypeAnswerMap);
// listOfAnswers.stream().forEach((answer) -> {
// if (answerMap.keySet().contains(answer.getAnswerType())) {
// answerMap.replace(answer.getAnswerType(), answer);
// }
// });

// return new Answers(answerMap.values().toArray())
// }

// public List<Answer> listOfAnswers() {
// return Arrays.asList(
// new Answer<OffsetDateTime>(this.jobAcceptDate, "jobAcceptDate"),
// new Answer<OffsetDateTime>(this.jobSearchStartDate, "jobSearchStartDate"),
// new Answer<ValidWorkModel>(this.workModel, "workModel"),
// new Answer<ValidWorkContract>(this.workContract, "workContract"),
// new Answer<Integer>(this.jobApplicationCount, "jobApplicationCount"),
// new Answer<String>(this.jobTitle, "jobTitle"),
// new Answer<Integer>(this.yearsOfProExperience, "yearsOfProExperience"),
// new Answer<ValidEducationLevel>(this.educationLevel, "educationLevel"));
// }
// }
