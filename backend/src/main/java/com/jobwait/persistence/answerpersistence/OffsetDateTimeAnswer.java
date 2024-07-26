package com.jobwait.persistence.answerpersistence;

import java.time.OffsetDateTime;

import com.jobwait.domain.Answer;

public class OffsetDateTimeAnswer implements Answer<OffsetDateTime> {
    private OffsetDateTime value;
    private String type;

    public OffsetDateTimeAnswer() {
    }

    public OffsetDateTimeAnswer(String type, OffsetDateTime value) {
        this.type = type;
        this.value = value;
    }

    public OffsetDateTime getValue() {
        return this.value;
    }

    public void setValue(OffsetDateTime value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "(type -> " + this.type + "," + "value -> " + String.valueOf(this.value) + ")";
    }
}
