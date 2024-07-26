package com.jobwait.persistence.answerpersistence;

import java.sql.Types;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidWorkModel;

public class ValidWorkModelAnswer implements Answer<ValidWorkModel> {
    private ValidWorkModel value;
    public final int sqlType = Types.OTHER;
    private String type;

    public ValidWorkModelAnswer() {
    }

    public ValidWorkModelAnswer(String type, ValidWorkModel value) {
        this.type = type;
        this.value = value;
    }

    public ValidWorkModel getValue() {
        return this.value;
    }

    public void setValue(ValidWorkModel value) {
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
