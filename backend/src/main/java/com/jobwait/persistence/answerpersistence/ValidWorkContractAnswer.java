package com.jobwait.persistence.answerpersistence;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidWorkContract;

public class ValidWorkContractAnswer implements Answer<ValidWorkContract> {
    private ValidWorkContract value;
    private String type;

    public ValidWorkContractAnswer() {
    }

    public ValidWorkContractAnswer(String type, ValidWorkContract value) {
        this.type = type;
        this.value = value;
    }

    public ValidWorkContract getValue() {
        return this.value;
    }

    public void setValue(ValidWorkContract value) {
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
