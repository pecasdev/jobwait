package com.jobwait.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.jobwait.domain.Answer;
import com.jobwait.domain.AnswerType;
import com.jobwait.domain.Question;
import com.jobwait.domain.Questions;
import com.jobwait.spring.utils.Utils;

public class AnswerDeserializer extends StdDeserializer<Answer> {
    public AnswerDeserializer() {
        this(null);
    }

    public AnswerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Answer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);

        JsonNode questionKeyNode = node.get("questionKey");
        if (questionKeyNode == null) {
            throw new IOException("questionKey not found");
        }

        String questionKey = questionKeyNode.asText();
        Question question = Questions.questionFromKey(questionKey);

        JsonNode answerTypeNode = node.get("answerValue");
        if (answerTypeNode == null) {
            throw new IOException("answerValue not found");
        }

        AnswerType answerType = question.answerType;

        Object answerValue = Utils.mapper.treeToValue(node.get("answerValue"), Object.class);

        // assert valid data type
        if (!Answer.assertValidAnswerType(answerType, answerValue)) {
            throw new IOException(
                    "value %s does not match type %s".formatted(answerValue, answerType));
        }

        // assert valid enum value
        if (answerType == AnswerType.ENUM && !question.answerChoices.contains(answerValue)) {
            throw new IOException(
                    "%s is not in valid choices: %s".formatted(
                            answerValue, String.join(",", question.answerChoices)));
        }

        return new Answer(questionKey, answerType, answerValue);
    }
}
