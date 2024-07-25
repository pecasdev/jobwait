package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidEducationLevel;

public class ValidEducationLevelAnswer extends Answer<ValidEducationLevel> {
    private ValidEducationLevel answerValue;
    private String answerType;

    public ValidEducationLevelAnswer(String answerType, ValidEducationLevel answerValue) {
        super();
    }

    public ValidEducationLevel getValue() {
        return this.answerValue;
    }
}
