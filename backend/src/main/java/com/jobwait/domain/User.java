package com.jobwait.domain;

import java.util.Arrays;
import java.util.List;

public record User(String userID, List<Prompt> prompts) {

    private static Prompt<Integer> intPrompt =  
        new Prompt<Integer>("1", "What?", new IntResponse(2));
    private static Prompt<String> strPrompt =  
        new Prompt<String>("1", "What?", new StrResponse("test"));

    private static List<User> users = Arrays.asList(
            new User("123", Arrays.asList(intPrompt)),
            new User("124", Arrays.asList(intPrompt, strPrompt)),
            new User("125", Arrays.asList(strPrompt))
    );

    public static User getById(String id) {
        User foundUser = users.stream()
                            .filter(user -> user.userID.equals(id))
                            .findFirst()
                      .orElse(null);
        return foundUser;
    }

}
