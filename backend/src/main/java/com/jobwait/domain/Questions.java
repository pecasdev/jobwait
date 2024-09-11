package com.jobwait.domain;

import java.io.IOException;
import java.util.List;

public class Questions {
    // touch this stuff
    public static List<String> workContractQuestionChoices = List.of("FULL_TIME", "PART_TIME", "CONTRACT",
            "INTERNSHIP", "OTHER");
    public static List<String> workModelQuestionChoices = List.of("ON_SITE", "HYBRID", "REMOTE");
    public static List<String> educationLevelQuestionChoices = List.of("LESS_THAN_HIGHSCHOOL", "HIGHSCHOOL_DIPLOMA",
            "ASSOCIATE_DEGREE", "BACHELOR_DEGREE", "MASTER_DEGREE", "DOCTORAL_DEGREE", "OTHER");

    public static List<Question> known = List.of(
            new Question("jobacceptdate", AnswerType.DATE),
            new Question("jobsearchstartdate", AnswerType.DATE),
            new Question("workmodel", AnswerType.ENUM, workModelQuestionChoices),
            new Question("workcontract", AnswerType.ENUM, workContractQuestionChoices),
            new Question("jobapplicationcount", AnswerType.INTEGER),
            new Question("jobtitle", AnswerType.STRING),
            new Question("yearsofproexperience", AnswerType.INTEGER),
            new Question("educationlevel", AnswerType.ENUM, educationLevelQuestionChoices));

    // don't touch this stuff
    public static List<String> knownQuestionKeys = Questions.known.stream().map(x -> x.key).toList();

    public static Question questionFromKey(String questionKey) throws IOException {
        return known.stream()
                .filter(q -> q.key.equals(questionKey))
                .findFirst().orElseThrow(() -> new IOException(
                        "questionKey '%s' does not exist in known questions"
                                .formatted(questionKey)));
    }
}
