package com.jobwait.domain;

public class Answer<T> {
    private T answerValue;
    private String answerType;

    public Answer(T answerValue) {
        this.answerValue = answerValue;
    }

    public Answer(T answerValue, String answerType) {
        this.answerValue = answerValue;
        this.answerType = answerType;
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
}
