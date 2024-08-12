package com.jobwait.domain;

import java.util.List;
import java.util.Map;

public class Stat {
    public String id;
    public Map<String, List<Integer>> rows;

    public Stat(String id, Map<String, List<Integer>> rows) {
        this.id = id;
        this.rows = rows;
    }

    String getId() {
        return this.id;
    }

    Map<String, List<Integer>> getRows() {
        return this.rows;
    }
}
