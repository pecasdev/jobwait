package com.jobwait.domain;

import java.time.OffsetDateTime;

/*
 * Q: why are we using Integer instead of int?
 * A: jackson, the deserializer spring uses for the JSON request body, sets any
 * missing parameters as null when creating an Answers record. Primitives in
 * java (int, double, float) cannot be null, so they get set to zero by jackson,
 * which is a problem. Using the class Integer fixes this issue.
 */
public record Answers(
        OffsetDateTime jobAcceptDate,
        OffsetDateTime jobSearchStartDate,
        ValidWorkModel workModel,
        ValidWorkContract workContract,
        Integer jobApplicationCount,
        String jobTitle,
        Integer yearsOfProExperience,
        ValidEducationLevel educationLevel) {
}
