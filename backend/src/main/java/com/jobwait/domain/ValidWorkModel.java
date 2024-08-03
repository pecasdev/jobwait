package com.jobwait.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ValidWorkModel {
    @JsonProperty("On-site")
    @JsonAlias("ON_SITE")
    ON_SITE,
    HYBRID,
    REMOTE
}
