package edu.hw7.task2;

import java.util.stream.LongStream;

public class Factorial {

    private Factorial() {
    }

    public static long calculate(int num) {
        return (num == 0) ? 1 : LongStream.range(1, num + 1)
            .parallel()
            .reduce(((left, right) -> left * right))
            .orElseThrow(IllegalArgumentException::new);
    }
}
