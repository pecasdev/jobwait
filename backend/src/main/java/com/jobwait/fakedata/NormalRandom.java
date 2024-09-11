package com.jobwait.fakedata;

import java.util.Random;

public class NormalRandom {
    private static Random random = new Random();

    static int sampleInt(int average, int std) {
        return (int) (random.nextGaussian() * std + average);
    }
}
