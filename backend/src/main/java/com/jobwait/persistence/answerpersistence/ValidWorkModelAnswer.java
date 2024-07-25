package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidWorkModel;

public class ValidWorkModelAnswer extends Answer<ValidWorkModel> {
    private ValidWorkModel answerValue;
    private String answerType;

    public ValidWorkModelAnswer(String answerType, ValidWorkModel answerValue) {
        super();
    }

    public ValidWorkModel getValue() {
        return this.answerValue;
    }
}
