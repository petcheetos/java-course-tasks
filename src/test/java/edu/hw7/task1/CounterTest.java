package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {
    private static final int COUNT = 100000;

    @Test
    void testIncrement() {
        Counter counter = new Counter();
        Thread incrementor1 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                counter.increment();
            }
        });
        Thread incrementor2 = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                counter.increment();
            }
        });
        incrementor1.start();
        incrementor2.start();

        try {
            incrementor1.join();
            incrementor2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(2 * COUNT, counter.getValue());
    }
}
