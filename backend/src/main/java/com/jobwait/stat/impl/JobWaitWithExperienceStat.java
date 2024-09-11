package com.jobwait.stat.impl;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;
import com.jobwait.stat.StatRows;

public class JobWaitWithExperienceStat extends Stat implements AnswerFetching {
    public JobWaitWithExperienceStat(String id) {
        super(id, "bar", "Wait time to receive job offer given years of experience", "days waited",
                "years of experience");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            LocalDate jobAcceptDate = null;
            LocalDate jobSearchStartDate = null;
            Integer yearsOfExperience = null;

            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobacceptdate")) {
                    jobAcceptDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("jobsearchstartdate")) {
                    jobSearchStartDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("yearsofproexperience")) {
                    yearsOfExperience = answer.answerValueAsInteger();
                }
            }

            if (jobAcceptDate != null && jobSearchStartDate != null && yearsOfExperience != null) {
                String daysElapsed = String.valueOf(ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate));
                this.rows.put(String.valueOf(yearsOfExperience), daysElapsed);
            }
        }
    }
}
