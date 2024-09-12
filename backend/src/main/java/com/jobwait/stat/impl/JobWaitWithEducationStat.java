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

public class JobWaitWithEducationStat extends Stat implements AnswerFetching {
    public JobWaitWithEducationStat(String id) {
        super(id, "bar", "Average wait time to receive job offer given education level", "Education Level",
                "Average Days Waited");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();
        HashMap<String, List<Integer>> educationLevelToJobWaitDays = new HashMap<String, List<Integer>>();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            LocalDate jobAcceptDate = null;
            LocalDate jobSearchStartDate = null;
            String educationLevel = null;

            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobacceptdate")) {
                    jobAcceptDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("jobsearchstartdate")) {
                    jobSearchStartDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("educationlevel")) {
                    educationLevel = answer.answerValueAsString();
                }
            }

            if (jobAcceptDate != null && jobSearchStartDate != null && educationLevel != null) {
                int daysElapsed = (int) ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate);
                
                if (!educationLevelToJobWaitDays.containsKey(educationLevel)) {
                    educationLevelToJobWaitDays.put(educationLevel, new ArrayList<Integer>());
                }
                educationLevelToJobWaitDays.get(educationLevel).add(daysElapsed);
            }
        }

        // compute averages and set rows
        for (String educationLevel : educationLevelToJobWaitDays.keySet()) {
            List<Integer> jobWaitDays = educationLevelToJobWaitDays.get(educationLevel);
            int jobWaitDaysSum = jobWaitDays.stream().mapToInt(Integer::intValue).sum();
            this.rows.put(educationLevel, jobWaitDaysSum / jobWaitDays.size());
        }
    }
}
