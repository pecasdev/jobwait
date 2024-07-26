package com.jobwait.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.OffsetDateTimeAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;

import static java.util.Map.entry;

public class Answers {
        public static final Map<String, Answer> ATypeAnswerMap = new HashMap<>(
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

        private List<Answer> answers;

        public Answers() {
        }

        public Answers(List<Answer> answers) {
                this.answers = answers;
        }

        public List<Answer> getListOfAnswers() {
                return this.answers;
        }

        public void setAnswers(List<Answer> answers) {
                this.answers = answers;
        }

        @Override
        public String toString() {
                return String.join("\n", this.answers.stream().map(answer -> answer.toString()).toList());
        }
}
