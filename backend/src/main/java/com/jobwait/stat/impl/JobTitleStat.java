package com.jobwait.stat.impl;

import java.util.List;
import java.util.Map;

import com.jobwait.domain.Stat;

public class JobTitleStat extends Stat {
    public JobTitleStat(String id) {
        super(id);
        this.rows = Map.of("programmer", 45, "scientist", 22);
    }

    public void fetchAndSetRows() {
        throw new UnsupportedOperationException("Unimplemented method 'fetchAndSetRows'");
    }
}
