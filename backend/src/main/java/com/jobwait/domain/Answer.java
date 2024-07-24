package com.jobwait.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jobwait.answerconversion.AnswerDeserializer;

@JsonDeserialize(using = AnswerDeserializer.class)
public class Answer<T> {
    private T answerValue;
    private String answerType;

    public Answer() {
        this.answerValue = null;
    }

    public Answer(String answerType, T answerValue) {
        this.answerValue = answerValue;
        this.answerType = answerType;
    }

    public Boolean isEmpty() {
        return this.answerValue == null;
    }

    public T getValue() {
        return this.answerValue;
    }

    public void setValue(T value) {
        this.answerValue = value;
    }

    public String getAnswerType() {
        return this.answerType;
    }

    public void setType(String type) {
        this.answerType = type;
    }
}
