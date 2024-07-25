package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidWorkContract;

public class ValidWorkContractAnswer extends Answer<ValidWorkContract> {
    private ValidWorkContract answerValue;
    private String answerType;

    public ValidWorkContractAnswer(String answerType, ValidWorkContract answerValue) {
        super();
    }

    public ValidWorkContract getValue() {
        return this.answerValue;
    }
}
