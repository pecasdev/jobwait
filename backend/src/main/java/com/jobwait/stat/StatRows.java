package com.jobwait.stat;

import java.util.HashMap;

public class StatRows extends HashMap<String, Object> {
    public void addOrIncrementRow(String key) {
        if (!super.containsKey(key)) {
            super.put(key, 0);
        }

        super.put(key, (int) super.get(key) + 1);
    }
}
