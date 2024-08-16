package com.jobwait.stat.impl;

import java.util.HashMap;

import com.jobwait.domain.Stat;
import com.jobwait.stat.AnswerFetching;

// for the purpose of having at least one line stat in the MVP
// this code should be deleted and replaced with a real stat
public class AppsNeededGivenJobCountStat extends Stat implements AnswerFetching {
    public AppsNeededGivenJobCountStat(String id) {
        super(id, "line");
        this.rows = new HashMap<String, Object>();
    }

    // todo - make this actually work
    // we would need to expand the current system to allow users
    // to submit and manage multiple answersets
    public void fetchAndSetRows() {
        this.rows = new HashMap<String, Object>();
        this.rows.put("1 job", 663);
        this.rows.put("2 jobs", 387);
        this.rows.put("3 jobs", 192);
        this.rows.put("4 jobs", 43);
    }
}
