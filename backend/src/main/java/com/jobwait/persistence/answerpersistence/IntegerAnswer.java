package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;

public class IntegerAnswer extends Answer<Integer> {
    private Integer answerValue;
    private String answerType;

    public IntegerAnswer(String answerType, Integer answerValue) {
        super();
    }

    public Integer getValue() {
        return this.answerValue;
    }
}
