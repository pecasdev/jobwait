package com.jobwait.domain;

import java.util.Map;

public abstract class Stat {
    public String id;
    protected Map<String, Object> rows;

    public Stat(String id) {
        this.id = id;
    }

    String getId() {
        return this.id;
    }

    public Map<String, Object> getRows() {
        return this.rows;
    }

    abstract public void fetchAndSetRows();
}
