package com.jobwait.fakedata;

import java.util.List;

public class ListRandom {
    static <K> K choose(List<K> choices) {
        return choices.get(IntRandom.sampleInt(0, choices.size() - 1));
    }
}
