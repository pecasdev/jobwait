package com.jobwait.fakedata;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    int NUMBER_OF_FAKE_USERS = 100;

    private List<User> createFakeUsers() {
        List<User> users = new ArrayList<User>();

        for (int i = 0; i != NUMBER_OF_FAKE_USERS; i++) {
            User user = postgresController.createUserFromAuthId("FAKE_".concat(UUID.randomUUID().toString()));
            users.add(user);
        }

        return users;
    }

    int JSSD_DAYS_AGO = 365;
    int JAD_DAYS_AFTER_START_MIN = 5;
    int JAD_DAYS_AFTER_START_MAX = 365;
    int JAD_DAYS_AFTER_START_AVG = 100;
    int JAD_DAYS_AFTER_START_STD = 30;
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
    int JAC_MIN = 10;
    int JAC_MAX = 5000;
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

    private void createFakeAnswersForUser(User user) {
        // jobsearchstartdate - everyone starts on the same day
        LocalDate jobSearchStartDate = LocalDate.now().minusDays(JSSD_DAYS_AGO);

        // jobacceptdate
        LocalDate jobAcceptDate = null;
        while (true) {
            jobAcceptDate = jobSearchStartDate
                    .plusDays(NormalRandom.sampleInt(JAD_DAYS_AFTER_START_AVG, JAD_DAYS_AFTER_START_STD));
            long daysAfterStart = ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate);

            if ((daysAfterStart >= JAD_DAYS_AFTER_START_MIN) && (daysAfterStart <= JAD_DAYS_AFTER_START_MAX)) {
                break;
            }
        }

        // workmodel
        String workModel = ListRandom.choose(WM_WEIGHTED_OPTIONS);

        // workcontract
        String workContract = ListRandom.choose(WC_WEIGHTED_OPTIONS);

        // jobapplicationcount
        int jobApplicationCount = IntRandom.sampleInt(JAC_MIN, JAC_MAX);

        // jobtitle
        String jobTitle = ListRandom.choose(JT_WEIGHTED_OPTIONS);

        // years of proexperience
        int yearsOfProExperience = -1;
        while (true) {
            yearsOfProExperience = NormalRandom.sampleInt(YOPE_AVG, YOPE_STD);
            if ((yearsOfProExperience >= YOPE_MIN) && (yearsOfProExperience <= YOPE_MAX)) {
                break;
            }
        }

        // educationlevel
        String educationLevel = ListRandom.choose(EL_WEIGHTED_OPTIONS);

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

    public void createFakeUsersAndAnswers() {
        List<User> users = createFakeUsers();
        System.out.println("Created %d users".formatted(users.size()));

        System.out.println("Creating fake answers...");
        for (User user : users) {
            createFakeAnswersForUser(user);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("createFakeData")) {
                System.out.println("creating fake data...");
                new FakeData().createFakeUsersAndAnswers();
                System.out.println("done creating fake data");
                return;
            }
        }
        System.out.println("missing argument 'createFakeData', aborting");
    }
}
