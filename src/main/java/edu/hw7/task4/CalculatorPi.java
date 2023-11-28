package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.DoubleAdder;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class CalculatorPi {
    private static final double COEFFICIENT = 4D;
    private static final double RADIUS = 0.5D;
    private static final double CENTRE = 0.5D;

    private CalculatorPi() {
    }

    public static double doSingleThreadAlgorithm(int iterations) {
        double totalCount = 0;
        double circleCount = 0;
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < iterations; i++) {
            double randomWidth = random.nextDouble();
            double randomHeight = random.nextDouble();
            if (pow(abs(randomWidth - CENTRE), 2) + pow(abs(randomHeight - CENTRE), 2) <= pow(RADIUS, 2)) {
                circleCount++;
            }
            totalCount++;
        }
        return COEFFICIENT * (circleCount / totalCount);
    }

    public static double doMultipleThreadsAlgorithm(int iterations, int threads) {
        DoubleAdder totalCount = new DoubleAdder();
        DoubleAdder circleCount = new DoubleAdder();
        try (ExecutorService service = Executors.newFixedThreadPool(threads)) {
            service.execute(() -> {
                Random random = ThreadLocalRandom.current();
                double totalCountCurr = 0D;
                double circleCountCurr = 0D;
                for (int i = 0; i < iterations / threads; i++) {
                    double randomWidth = random.nextDouble();
                    double randomHeight = random.nextDouble();
                    if (pow(abs(randomWidth - CENTRE), 2) + pow(abs(randomHeight - CENTRE), 2) <= pow(RADIUS, 2)) {
                        circleCountCurr++;
                    }
                    totalCountCurr++;
                }
                totalCount.add(totalCountCurr);
                circleCount.add(circleCountCurr);
            });
            service.shutdown();
        }
        return COEFFICIENT * (circleCount.sum() / totalCount.sum());
    }
}
