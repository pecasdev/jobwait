package com.jobwait.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jobwait.jackson.AnswerDeserializer;

@JsonDeserialize(using = AnswerDeserializer.class)
public class Answer {
    public String questionKey;
    public AnswerType answerType;
    public Object answerValue;

    public Answer(String questionKey, AnswerType answerType, Object answerValue) {
        this.questionKey = questionKey;
        this.answerType = answerType;
        this.answerValue = answerValue;
    }

    public String getQuestionKey() {
        return this.questionKey;
    }

    public AnswerType getAnswerType() {
        return this.answerType;
    }

    public Object getAnswerValue() {
        switch (this.getAnswerType()) {
            case AnswerType.STRING:
                return this.answerValueAsString();
            case AnswerType.INTEGER:
                return this.answerValueAsInteger();
            case AnswerType.ENUM:
                return this.answerValueAsString();
            case AnswerType.DATE:
                return this.answerValueAsDate();
            default:
                throw new RuntimeException("Answer type not defined (impossible error)");
        }
    }

    public String answerValueAsString() {
        return (String) this.answerValue;
    }

    public Integer answerValueAsInteger() {
        return (Integer) this.answerValue;
    }

    public LocalDate answerValueAsDate() {
        return (LocalDate) this.answerValue;
    }

    public static boolean assertValidAnswerType(AnswerType answerType, Object answerValue) {
        switch (answerType) {
            case AnswerType.STRING:
                return answerValue.getClass().equals(String.class);
            case AnswerType.INTEGER:
                return answerValue.getClass().equals(Integer.class);
            case AnswerType.ENUM:
                return answerValue.getClass().equals(String.class);
            case AnswerType.DATE:
                return answerValue.getClass().equals(LocalDate.class);
            default:
                return false;
        }
    }
}
