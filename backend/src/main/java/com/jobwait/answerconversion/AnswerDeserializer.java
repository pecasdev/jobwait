package com.jobwait.answerconversion;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.jobwait.domain.Answer;
import com.jobwait.domain.Answers;

public class AnswerDeserializer extends JsonDeserializer<List<Answer>> implements ContextualDeserializer {
    private JavaType type;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) {
        this.type = property.getType().containedType(0);
        return this;
    }

    @Override
    public List<Answer> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        if (jsonParser.currentToken() == JsonToken.START_ARRAY) {
            List<Answer> answers = new ArrayList<>();
            Answer<?> answer = new Answer<>();
            jsonParser.nextToken();

            while (jsonParser.hasCurrentToken() && jsonParser.currentToken() != JsonToken.END_ARRAY) {
                if (jsonParser.currentToken() == JsonToken.START_OBJECT) {
                    jsonParser.nextValue(); // skip field name "type" or "value"

                    String jsonVal = jsonParser.getValueAsString();
                    if (Answers.ATypeAnswerMap.containsKey(jsonVal)) {
                        answer = Answers.ATypeAnswerMap.get(jsonVal);
                        answer.setType(jsonVal);
                        jsonParser.nextToken();
                        if (jsonParser.hasCurrentToken() && jsonParser.currentToken() != JsonToken.END_OBJECT) {
                            JavaType answerType = deserializationContext
                                    .constructType(Answers.ATypeAnswerMap.get(jsonVal).getClass());
                            answer.setValue(deserializationContext.readValue(jsonParser, answerType));
                        }
                    }
                }
                answers.add(answer);
            }
            return answers;
        }
        return null;
    }
}
