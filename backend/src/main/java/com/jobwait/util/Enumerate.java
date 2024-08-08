package com.jobwait.util;

import java.util.ArrayList;
import java.util.List;

public class Enumerate {
    static public <T> List<Tuple<T, Integer>> zipWithIndex(List<T> elems) {
        List<Tuple<T, Integer>> result = new ArrayList<Tuple<T, Integer>>();
        for (int i = 0; i != elems.size(); i++) {
            result.add(new Tuple<T, Integer>(elems.get(i), i));
        }
        return result;
    }
}
