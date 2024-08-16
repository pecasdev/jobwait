package com.jobwait.stat.impl;

import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;

// wowowowow it's the name of the website guys, this is THE stat
public class JobWaitStat extends Stat implements AnswerFetching {
    public JobWaitStat(String id) {
        super(id, "bar");
        this.rows = new HashMap<String, Object>();
    }

    // todo - put this into a defaultdict util class
    private void addOrIncrementRow(int daysWaited) {
        String key = String.valueOf(daysWaited).concat(" day");
        if (daysWaited > 1) {
            key = key.concat("s");
        }
        if (this.rows.containsKey(key)) {
            int prev = (int) this.rows.get(key);
            this.rows.put(key, prev + 1);
        } else {
            this.rows.put(key, 1);
        }
    }

    public void fetchAndSetRows() {
        this.rows = new HashMap<String, Object>();

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
                int daysElapsed = (int) ChronoUnit.DAYS.between(jobSearchStartDate, jobAcceptDate);
                this.addOrIncrementRow(daysElapsed);
            }
        }
    }
}
