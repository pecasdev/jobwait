package com.jobwait.domain;

import java.util.List;

public class Question {
    public String key;
    public AnswerType answerType;
    public List<String> answerChoices;

    public Question(String key, AnswerType answerType, List<String> answerChoices) {
        this.key = key;
        this.answerType = answerType;
        this.answerChoices = answerChoices;

        if (answerType == AnswerType.ENUM && (answerChoices == null || answerChoices.size() == 0)) {
            throw new InvalidQuestionDefinitionFault(key);
        }
    }

    public Question(String key, AnswerType answerType) {
        this.key = key;
        this.answerType = answerType;
        this.answerChoices = null;

        if (answerType == AnswerType.ENUM) {
            throw new InvalidQuestionDefinitionFault(key);
        }
    }
}
