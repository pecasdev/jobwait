package com.jobwait.stat.impl;

import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;
import com.jobwait.stat.StatRows;

public class JobTitleStat extends Stat implements AnswerFetching {
    public JobTitleStat(String id) {
        super(id, "bar", "Job Title", "Title", "# of people");
    }

    public void fetchAndSetRows() {
        this.rows = new StatRows();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobtitle")) {
                    String title = answer.answerValueAsString();
                    if (title != null) {
                        this.rows.addOrIncrementRow(answer.answerValueAsString());
                    }
                }
            }
        }
    }
}
