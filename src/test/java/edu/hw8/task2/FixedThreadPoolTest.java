package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedThreadPoolTest {

    @Test
    void testCalculate() {
        try (FixedThreadPool threadPool = FixedThreadPool.create(2)) {
            List<Integer> result = new ArrayList<>();
            threadPool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    int fib = Fibonacci.calculate(i);
                    result.add(fib);
                }
            });
            List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
            assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
        }
    }
}
