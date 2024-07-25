package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;

public class StringAnswer extends Answer<String> {
    private String answerValue;
    private String answerType;

    public StringAnswer(String answerType, String answerValue) {
        super();
    }

    public String getValue() {
        return this.answerValue;
    }
}
