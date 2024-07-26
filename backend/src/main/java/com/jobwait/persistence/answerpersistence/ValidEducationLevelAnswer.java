package com.jobwait.persistence.answerpersistence;

import java.sql.Types;

import com.jobwait.domain.Answer;
import com.jobwait.domain.ValidEducationLevel;

public class ValidEducationLevelAnswer implements Answer<ValidEducationLevel> {
    private ValidEducationLevel value;
    public final int sqlType = Types.OTHER;
    private String type;

    public ValidEducationLevelAnswer() {
    }

    public ValidEducationLevelAnswer(String type, ValidEducationLevel value) {
        this.type = type;
        this.value = value;
    }

    public ValidEducationLevel getValue() {
        return this.value;
    }

    public void setValue(ValidEducationLevel value) {
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
