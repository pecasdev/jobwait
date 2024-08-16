package com.jobwait.stat.impl;

import java.util.HashMap;
import java.util.List;

import com.jobwait.domain.Answer;
import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;

public class JobTitleStat extends Stat implements AnswerFetching {
    public JobTitleStat(String id) {
        super(id, "bar");
        this.rows = new HashMap<String, Object>();
    }

    // todo - put this into a defaultdict util class
    private void addOrIncrementRow(String title) {
        if (this.rows.containsKey(title)) {
            int prev = (int) this.rows.get(title);
            this.rows.put(title, prev + 1);
        } else {
            this.rows.put(title, 1);
        }
    }

    public void fetchAndSetRows() {
        this.rows = new HashMap<String, Object>();

        List<List<Answer>> answers = this.fetchAllAnswerSets();
        for (List<Answer> answerSet : answers) {
            for (Answer answer : answerSet) {
                if (answer.questionKey.equals("jobtitle")) {
                    String title = answer.answerValueAsString();
                    if (title != null) {
                        this.addOrIncrementRow(answer.answerValueAsString());
                    }
                }
            }
        }
    }
}
