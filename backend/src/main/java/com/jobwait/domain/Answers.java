package com.jobwait.domain;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/*
 * Q: why are we using Integer instead of int?
 * A: jackson, the deserializer spring uses for the JSON request body, sets any
 * missing parameters as null when creating an Answers record. Primitives in
 * java (int, double, float) cannot be null, so they get set to zero by jackson,
 * which is a problem. Using the class Integer fixes this issue.
 */
public class Answers {
        public OffsetDateTime jobAcceptDate;
        public OffsetDateTime jobSearchStartDate;
        public ValidWorkModel workModel;
        public ValidWorkContract workContract;
        public Integer jobApplicationCount;
        public String jobTitle;
        public Integer yearsOfProExperience;
        public ValidEducationLevel educationLevel;

        public Answers() {
                this.jobAcceptDate = null;
                this.jobSearchStartDate = null;
                this.workModel = null;
                this.workContract = null;
                this.jobApplicationCount = null;
                this.jobTitle = null;
                this.yearsOfProExperience = null;
                this.educationLevel = null;
        }

        public Answers(OffsetDateTime jobAcceptDate,
                        OffsetDateTime jobSearchStartDate,
                        ValidWorkModel workModel,
                        ValidWorkContract workContract,
                        Integer jobApplicationCount,
                        String jobTitle,
                        Integer yearsOfProExperience,
                        ValidEducationLevel educationLevel) {
                this.jobAcceptDate = jobAcceptDate;
                this.jobSearchStartDate = jobSearchStartDate;
                this.workModel = workModel;
                this.workContract = workContract;
                this.jobApplicationCount = jobApplicationCount;
                this.jobTitle = jobTitle;
                this.yearsOfProExperience = yearsOfProExperience;
                this.educationLevel = educationLevel;
        }

        public Answers(List<Answer> listOfAnswers) {
                this.jobAcceptDate = (OffsetDateTime) listOfAnswers.get(0).getValue();
                this.jobSearchStartDate = (OffsetDateTime) listOfAnswers.get(1).getValue();
                this.workModel = (ValidWorkModel) listOfAnswers.get(2).getValue();
                this.workContract = (ValidWorkContract) listOfAnswers.get(3).getValue();
                this.jobApplicationCount = (Integer) listOfAnswers.get(4).getValue();
                this.jobTitle = (String) listOfAnswers.get(5).getValue();
                this.yearsOfProExperience = (Integer) listOfAnswers.get(6).getValue();
                this.educationLevel = (ValidEducationLevel) listOfAnswers.get(7).getValue();
        }

        public List<Answer> listOfAnswers() {
                return Arrays.asList(
                                new Answer<OffsetDateTime>(this.jobAcceptDate, "jobAcceptDate"),
                                new Answer<OffsetDateTime>(this.jobSearchStartDate, "jobSearchStartDate"),
                                new Answer<ValidWorkModel>(this.workModel, "workModel"),
                                new Answer<ValidWorkContract>(this.workContract, "workContract"),
                                new Answer<Integer>(this.jobApplicationCount, "jobApplicationCount"),
                                new Answer<String>(this.jobTitle, "jobTitle"),
                                new Answer<Integer>(this.yearsOfProExperience, "yearsOfProExperience"),
                                new Answer<ValidEducationLevel>(this.educationLevel, "educationLevel"));
        }
}
