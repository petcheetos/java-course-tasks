package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger value = new AtomicInteger(0);

    public void increment() {
        value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}
