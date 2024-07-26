package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;

public class StringAnswer implements Answer<String> {
    private String value;
    private String type;

    public StringAnswer() {

    }

    public StringAnswer(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
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
        return "(type -> " + this.type + "," + "value -> " + this.value + ")";
    }
}
