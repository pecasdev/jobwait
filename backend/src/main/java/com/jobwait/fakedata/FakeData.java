package com.jobwait.fakedata;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import com.jobwait.domain.Answer;
import com.jobwait.domain.AnswerType;
import com.jobwait.domain.User;
import com.jobwait.persistence.PostgresController;

// this class is responsible for faking answers for the questions
public class FakeData {
    PostgresController postgresController = new PostgresController();

    public static int NUMBER_OF_FAKE_USERS = 1000;

    public User createFakeUser() {
        User user = postgresController.createUserFromAuthId("FAKE_".concat(UUID.randomUUID().toString()));
        return user;
    }

    int JSSD_DAYS_AGO = 365 * 3;
    int JAD_DAYS_AFTER_START_MIN = 5;
    int JAD_DAYS_AFTER_START_MAX = 1000;
    int JAD_DAYS_AFTER_START_AVG = 100;
    int JAD_DAYS_AFTER_START_STD = 30;
    Map<String, Integer> JAD_DAYS_AFTER_START_EDUCATION_MODIFIERS = Map.of(
            "HIGHSCHOOL_DIPLOMA", 3,
            "BACHELOR_DEGREE", 2,
            "MASTER_DEGREE", 1,
            "DOCTORAL_DEGREE", 1);
    Map<Integer, Integer> JAD_DAYS_AFTER_START_YOE_MODIFIERS = Map.of(
            0, 4,
            1, 3,
            2, 3,
            3, 2,
            4, 2,
            5, 1,
            6, 1);
    List<String> WM_WEIGHTED_OPTIONS = Stream.of(
            Collections.nCopies(20, "ON_SITE").stream(),
            Collections.nCopies(10, "HYBRID").stream(),
            Collections.nCopies(5, "REMOTE").stream()).flatMap(Function.identity()).toList();
    List<String> WC_WEIGHTED_OPTIONS = Stream.of(
            Collections.nCopies(20, "FULL_TIME").stream(),
            Collections.nCopies(10, "PART_TIME").stream(),
            Collections.nCopies(5, "CONTRACT").stream(),
            Collections.nCopies(2, "INTERNSHIP").stream(),
            Collections.nCopies(1, "OTHER").stream()).flatMap(Function.identity()).toList();
    int JAC_MIN = 50;
    int JAC_MAX = 100000;
    int JAC_STD = 250;
    List<String> JT_WEIGHTED_OPTIONS = Stream.of(
            Collections.nCopies(20, "Fullstack Developer").stream(),
            Collections.nCopies(10, "Frontend Developer").stream(),
            Collections.nCopies(5, "Backend Developer").stream(),
            Collections.nCopies(2, "ML Engineer").stream()).flatMap(Function.identity()).toList();
    int YOPE_AVG = 3;
    int YOPE_STD = 1;
    int YOPE_MIN = 0;
    int YOPE_MAX = 10;
    List<String> EL_WEIGHTED_OPTIONS = Stream.of(
            Collections.nCopies(5, "HIGHSCHOOL_DIPLOMA").stream(),
            Collections.nCopies(30, "BACHELOR_DEGREE").stream(),
            Collections.nCopies(15, "MASTER_DEGREE").stream(),
            Collections.nCopies(1, "DOCTORAL_DEGREE").stream()).flatMap(Function.identity()).toList();

    public void createFakeAnswersForUser(User user) {
        // educationlevel
        String educationLevel = ListRandom.choose(EL_WEIGHTED_OPTIONS);

        // years of proexperience
        int yearsOfProExperience = -1;
        while (true) {
            yearsOfProExperience = NormalRandom.sampleInt(YOPE_AVG, YOPE_STD);
            if ((yearsOfProExperience >= YOPE_MIN) && (yearsOfProExperience <= YOPE_MAX)) {
                break;
            }
        }

        // jobsearchstartdate - everyone starts on the same day
        LocalDate jobSearchStartDate = LocalDate.now().minusDays(JSSD_DAYS_AGO);

        // jobacceptdate
        LocalDate jobAcceptDate = null;
        int jobAcceptDateDaysAfterSearchStartDate;

        int jadDaysAfterStartAverageWithEducation = JAD_DAYS_AFTER_START_AVG
                * JAD_DAYS_AFTER_START_EDUCATION_MODIFIERS.get(educationLevel);
        int jadDaysAfterStartAverageWithEducationAndYearsOfExperience = jadDaysAfterStartAverageWithEducation
                * JAD_DAYS_AFTER_START_YOE_MODIFIERS.get(yearsOfProExperience);

        while (true) {
            jobAcceptDate = jobSearchStartDate
                    .plusDays(NormalRandom.sampleInt(jadDaysAfterStartAverageWithEducationAndYearsOfExperience,
                            JAD_DAYS_AFTER_START_STD));
            jobAcceptDateDaysAfterSearchStartDate = (int) ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate);

            if ((jobAcceptDateDaysAfterSearchStartDate >= JAD_DAYS_AFTER_START_MIN)
                    && (jobAcceptDateDaysAfterSearchStartDate <= JAD_DAYS_AFTER_START_MAX)) {
                break;
            }
        }

        // workmodel
        String workModel = ListRandom.choose(WM_WEIGHTED_OPTIONS);

        // workcontract
        String workContract = ListRandom.choose(WC_WEIGHTED_OPTIONS);

        // jobapplicationcount - assume 10 applications per day to guarantee success (LOL)
        int jobApplicationCount;
        while (true) {
            jobApplicationCount = NormalRandom.sampleInt(
                    jobAcceptDateDaysAfterSearchStartDate * 10, JAC_STD);

            if ((jobApplicationCount >= JAC_MIN) && (jobApplicationCount <= JAC_MAX)) {
                break;
            }
        }

        // jobtitle
        String jobTitle = ListRandom.choose(JT_WEIGHTED_OPTIONS);

        // submit answers
        List<Answer> answers = List.of(
                new Answer("jobacceptdate", AnswerType.DATE, jobAcceptDate),
                new Answer("jobsearchstartdate", AnswerType.DATE, jobSearchStartDate),
                new Answer("workmodel", AnswerType.ENUM, workModel),
                new Answer("workcontract", AnswerType.ENUM, workContract),
                new Answer("jobapplicationcount", AnswerType.INTEGER, jobApplicationCount),
                new Answer("jobtitle", AnswerType.STRING, jobTitle),
                new Answer("yearsofproexperience", AnswerType.INTEGER, yearsOfProExperience),
                new Answer("educationlevel", AnswerType.ENUM, educationLevel));

        postgresController.updateUserAnswers(user, answers);
    }
}
