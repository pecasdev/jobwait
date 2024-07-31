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
                mapping.put("jobacceptdate", new OffsetDateTimeAnswer("jobacceptdate", null));
                mapping.put("jobsearchstartdate", new OffsetDateTimeAnswer("jobsearchstartdate", null));
                mapping.put("workmodel", new ValidWorkModelAnswer("workmodel", null));
                mapping.put("workcontract", new ValidWorkContractAnswer("workcontract", null));
                mapping.put("jobapplicationcount", new IntegerAnswer("jobapplicationcount", null));
                mapping.put("jobtitle", new StringAnswer("jobtitle", null));
                mapping.put("yearsofproexperience", new IntegerAnswer("yearsofproexperience", null));
                mapping.put("educationlevel", new ValidEducationLevelAnswer("educationlevel", null));
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
