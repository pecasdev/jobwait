package com.jobwait.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ValidWorkContract {
    @JsonProperty("Full-time")
    @JsonAlias("FULL_TIME")
    FULL_TIME,
    @JsonProperty("Part-time")
    @JsonAlias("PART_TIME")
    PART_TIME,
    CONTRACT,
    INTERNSHIP,
    OTHER
}
