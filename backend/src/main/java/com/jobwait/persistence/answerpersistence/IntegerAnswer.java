package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;

public class IntegerAnswer implements Answer<Integer> {
    private Integer value;

    private String type;

    public IntegerAnswer() {
    }

    public IntegerAnswer(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
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
