package com.jobwait.fakedata;

import java.util.Random;

public class IntRandom {
    private static Random random = new Random();

    static int sampleInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
