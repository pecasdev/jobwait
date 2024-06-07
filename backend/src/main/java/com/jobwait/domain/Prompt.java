package com.jobwait.domain;

import java.util.Arrays;
import java.util.List;

public record Prompt<T extends Object>(String id, String question, Response<T> response) {

    private static List<Prompt> prompts = Arrays.asList(
            new Prompt<Integer>("1", "What?", new IntResponse(2)),
            new Prompt<String>("2", "Why?", new StrResponse("test")),
            new Prompt<String>("3", "How?", new StrResponse("testser"))
    );

    public static Prompt getById(String id) {
        return prompts.stream()
				.filter(prompt -> prompt.id().equals(id))
				.findFirst()
				.orElse(null);
    }
}
