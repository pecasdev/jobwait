package com.jobwait.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.jobwait.persistence.answerpersistence.IntegerAnswer;
import com.jobwait.persistence.answerpersistence.OffsetDateTimeAnswer;
import com.jobwait.persistence.answerpersistence.StringAnswer;
import com.jobwait.persistence.answerpersistence.ValidEducationLevelAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkContractAnswer;
import com.jobwait.persistence.answerpersistence.ValidWorkModelAnswer;

public class Answers {
        public static final Map<String, Answer> ATypeAnswerMap = Answers.createMap();

        public static final List<String> listOfTypeOfAnswers = new ArrayList<>(ATypeAnswerMap.keySet());

        // Map.ofEntries() was giving me trouble with order, this helps for some reason.
        // Leave this alone.
        private static Map<String, Answer> createMap() {
                Map<String, Answer> mapping = new LinkedHashMap<>();
                mapping.put("jobAcceptDate", new OffsetDateTimeAnswer("jobAcceptDate", null));
                mapping.put("jobSearchStartDate", new OffsetDateTimeAnswer("jobSearchStartDate", null));
                mapping.put("workModel", new ValidWorkModelAnswer("workModel", null));
                mapping.put("workContract", new ValidWorkContractAnswer("workContract", null));
                mapping.put("jobApplicationCount", new IntegerAnswer("jobApplicationCount", null));
                mapping.put("jobTitle", new StringAnswer("jobTitle", null));
                mapping.put("yearsOfProExperience", new IntegerAnswer("yearsOfProExperience", null));
                mapping.put("educationLevel", new ValidEducationLevelAnswer("educationLevel", null));
                return mapping;
        }

        private List<Answer> answers;

        public Answers() {
        }

        public Answers(List<Answer> answers) {
                this.answers = answers;
        }

        public List<Answer> getAnswers() {
                return this.answers;
        }

        public void setAnswers(List<Answer> answers) {
                this.answers = answers;
        }

        @Override
        public String toString() {
                return "Answers : {\n\t"
                                + String.join("\n\t", this.answers.stream().map(answer -> answer.toString()).toList())
                                + "\n}";
        }
}
