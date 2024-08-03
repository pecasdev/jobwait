package com.jobwait.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ValidEducationLevel {
    @JsonProperty("Less than high school")
    @JsonAlias("LESS_THAN_HIGHSCHOOL")
    LESS_THAN_HIGHSCHOOL,
    @JsonProperty("High school Diploma")
    @JsonAlias("HIGHSCHOOL_DIPLOMA")
    HIGHSCHOOL_DIPLOMA,
    @JsonProperty("Associate Degree")
    @JsonAlias("ASSOCIATE_DEGREE")
    ASSOCIATE_DEGREE,
    @JsonProperty("Bachelor's Degree")
    @JsonAlias("BACHELOR_DEGREE")
    BACHELOR_DEGREE,
    @JsonProperty("Master's Degree")
    @JsonAlias("MASTER_DEGREE")
    MASTER_DEGREE,
    @JsonProperty("Doctorate Degree")
    @JsonAlias("DOCTORAL_DEGREE")
    DOCTORAL_DEGREE,
    OTHER;
}
