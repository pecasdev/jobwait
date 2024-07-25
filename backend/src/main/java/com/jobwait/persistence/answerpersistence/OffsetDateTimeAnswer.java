package com.jobwait.persistence.answerpersistence;

import java.time.OffsetDateTime;

import com.jobwait.domain.Answer;

public class OffsetDateTimeAnswer extends Answer<OffsetDateTime> {
    private OffsetDateTime answerValue;
    private String answerType;

    public OffsetDateTimeAnswer(String answerType, OffsetDateTime answerValue) {
        super();
    }

    public OffsetDateTime getValue() {
        return this.answerValue;
    }
}
