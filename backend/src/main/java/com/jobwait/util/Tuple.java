package com.jobwait.util;

public class Tuple<X, Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getLeft() {
        return this.x;
    }

    public Y getRight() {
        return this.y;
    }
}
