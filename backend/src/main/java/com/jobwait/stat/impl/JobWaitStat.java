package com.jobwait.stat.impl;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;
import com.jobwait.stat.StatRows;

// wowowowow it's the name of the website guys, this is THE stat
public class JobWaitStat extends Stat implements AnswerFetching {
    public JobWaitStat(String id) {
        super(id, "bar", "Wait time to receive job offer", "days waited", "# of people");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            LocalDate jobAcceptDate = null;
            LocalDate jobSearchStartDate = null;

            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobacceptdate")) {
                    jobAcceptDate = answer.answerValueAsDate();
                }

                if (answer.questionKey.equals("jobsearchstartdate")) {
                    jobSearchStartDate = answer.answerValueAsDate();
                }
            }

            if (jobAcceptDate != null && jobSearchStartDate != null) {
                String daysElapsed = String.valueOf(ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate));
                this.rows.addOrIncrementRow(daysElapsed);
            }
        }
    }
}
