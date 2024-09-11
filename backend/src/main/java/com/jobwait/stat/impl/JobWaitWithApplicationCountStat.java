package com.jobwait.stat.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;
import com.jobwait.stat.StatRows;

public class JobWaitWithApplicationCountStat extends Stat implements AnswerFetching {
    public JobWaitWithApplicationCountStat(String id) {
        super(id, "bar", "Average wait time to receive job offer given number of applications", "application count",
                "average wait time");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();
        HashMap<String, List<Integer>> applicationCountToJobWaitDays = new HashMap<String, List<Integer>>();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            LocalDate jobAcceptDate = null;
            LocalDate jobSearchStartDate = null;
            String applicationCount = null;

            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobacceptdate")) {
                    jobAcceptDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("jobsearchstartdate")) {
                    jobSearchStartDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("jobapplicationcount")) {
                    applicationCount = String.valueOf(answer.answerValueAsInteger());
                }
            }

            if (jobAcceptDate != null && jobSearchStartDate != null && applicationCount != null) {
                int daysElapsed = (int) ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate);
                
                if (!applicationCountToJobWaitDays.containsKey(applicationCount)) {
                    applicationCountToJobWaitDays.put(applicationCount, new ArrayList<Integer>());
                }
                applicationCountToJobWaitDays.get(applicationCount).add(daysElapsed);
            }
        }

        // compute averages and set rows
        for (String applicationCount : applicationCountToJobWaitDays.keySet()) {
            List<Integer> jobWaitDays = applicationCountToJobWaitDays.get(applicationCount);
            int jobWaitDaysSum = jobWaitDays.stream().mapToInt(Integer::intValue).sum();
            this.rows.put(applicationCount, jobWaitDaysSum / jobWaitDays.size());
        }
    }
}
