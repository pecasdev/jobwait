package com.jobwait.domain;

import java.util.Map;

public abstract class Stat {
    public String id;
    public String type;
    protected Map<String, Object> rows;

    public Stat(String id, String type) {
        this.id = id;
        this.type = type;
    }

    String getId() {
        return this.id;
    }

    public Map<String, Object> getRows() {
        return this.rows;
    }

    abstract public void fetchAndSetRows();
}
