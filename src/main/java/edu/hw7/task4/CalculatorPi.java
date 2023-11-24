package edu.hw7.task4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Stream;

public class CalculatorPi {
    public static final double COEFFICIENT = 4D;
    public static final double RADIUS = 1D;

    private CalculatorPi() {
    }

    public static double doSingleThreadAlgorithm(int iterations) {
        double totalCount = 0;
        double circleCount = 0;
        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            double randomWidth = random.nextDouble();
            double randomHeight = random.nextDouble();
            if (randomWidth * randomWidth + randomHeight * randomHeight <= RADIUS * RADIUS) {
                circleCount++;
            }
            totalCount++;
        }
        return COEFFICIENT * (circleCount / totalCount);
    }

    public static double doMultipleThreadsAlgorithm(int iterations, int threads) {
        DoubleAdder totalCount = new DoubleAdder();
        DoubleAdder circleCount = new DoubleAdder();

        List<Thread> threadList = Stream.generate(
            () -> new Thread(() -> {
                Random random = ThreadLocalRandom.current();
                double totalCountCurr = 0D;
                double circleCountCurr = 0D;
                for (int i = 0; i < iterations / threads; i++) {
                    double randomWidth = random.nextDouble();
                    double randomHeight = random.nextDouble();
                    if (randomWidth * randomWidth + randomHeight * randomHeight <= RADIUS * RADIUS) {
                        circleCountCurr++;
                    }
                    totalCountCurr++;
                }
                totalCount.add(totalCountCurr);
                circleCount.add(circleCountCurr);
            })).limit(threads).toList();

        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return COEFFICIENT * (circleCount.sum() / totalCount.sum());
    }
}
