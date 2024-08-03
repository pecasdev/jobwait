package com.jobwait.spring.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Utils {
    public final static ObjectMapper mapper = JsonMapper.builder()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false) // otherwise you get "2024-08-09" as
                                                                              // [2024, 08, 09]
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) // used for "" -> null
            .addModule(new JavaTimeModule()).build();
}
