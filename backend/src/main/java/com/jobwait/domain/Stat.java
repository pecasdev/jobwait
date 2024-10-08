package com.jobwait.domain;

import java.util.Map;

import com.jobwait.stat.StatRows;

public abstract class Stat {
    // internal identifier used to fetch the stat
    public String id;

    // the type of graph the frontend should use to display rows
    public String type;

    // the title to display
    public String title;

    // the label to display on the x-axis
    public String xAxisLabel;

    // the label to display on the y-axis
    public String yAxisLabel;

    // row data to use when displaying rows on the frontend
    protected StatRows rows;

    public Stat(String id, String type, String title, String xAxisLabel, String yAxisLabel) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.rows = new StatRows();
    }

    public Map<String, Object> getRows() {
        return this.rows;
    }

    abstract public void fetchAndSetRows();
}
