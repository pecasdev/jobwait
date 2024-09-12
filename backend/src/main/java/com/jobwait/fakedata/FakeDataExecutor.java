package com.jobwait.fakedata;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.jobwait.domain.User;

public class FakeDataExecutor {
    FakeData fakeData = new FakeData();

    private Callable<Void> createFakeUsersAndAnswersHandle() {
        return () -> {
            User user = fakeData.createFakeUser();
            fakeData.createFakeAnswersForUser(user);
            return null;
        };
    }

    int INDIVIDUAL_FAKERY_TIMEOUT_SECONDS = 10;
    int OVERALL_FAKERY_TIMEOUT_SECONDS = 60;
    int THREAD_COUNT = 100;

    public void createFakeUsersAndAnswers() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i != FakeData.NUMBER_OF_FAKE_USERS; i++) {
            executor.submit(() -> {
                ExecutorService individual = Executors.newSingleThreadExecutor();

                Future<Void> future = individual.submit(createFakeUsersAndAnswersHandle());

                try {
                    future.get(INDIVIDUAL_FAKERY_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    System.err.println(
                            "1 task out of %d has timed out, skipping".formatted(FakeData.NUMBER_OF_FAKE_USERS));
                    future.cancel(true);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("Task interrupted or failed");
                    System.err.print(e);
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(OVERALL_FAKERY_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                System.out.println("Could not finish all tasks in time");

                ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;
                int completedCount = (int) tpe.getCompletedTaskCount();
                System.out.println("Finished %d tasks before timing out".formatted(completedCount));

                executor.shutdownNow();
                if (!executor.awaitTermination(OVERALL_FAKERY_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("createFakeData")) {
                System.out.println("creating fake data... (should take under a minute)");
                new FakeDataExecutor().createFakeUsersAndAnswers();
                System.out.println(
                        "done! cleaning up and exiting... (I sometimes hang for some reason, feel free to kill me)");
                return;
            }
        }
        System.out.println("missing argument 'createFakeData', aborting");
    }
}
