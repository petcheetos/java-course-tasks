package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorPiTest {
    private final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void testSingleThreadAlgorithm(int arg) {
        double result = CalculatorPi.doSingleThreadAlgorithm(arg);
        assertTrue(3.12D < result && result < 3.16D);
        LOGGER.info("single thread accuracy = " + abs(Math.PI - result));
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void testMultipleThreadsAlgorithm(int arg) {
        double result = CalculatorPi.doMultipleThreadsAlgorithm(arg, 8);
        assertTrue(3.12D < result && result < 3.16D);
        LOGGER.info("multi thread accuracy = " + abs(Math.PI - result));
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void performanceTest(int arg) {
        long startTimeSingleAlg = System.currentTimeMillis();
        CalculatorPi.doSingleThreadAlgorithm(arg);
        long endTimeSingleAlg = System.currentTimeMillis();
        long timeElapsedSingleAlg = endTimeSingleAlg - startTimeSingleAlg;

        long startTimeMultipleAlg = System.currentTimeMillis();
        CalculatorPi.doMultipleThreadsAlgorithm(arg, 8);
        long endTimeMultipleAlg = System.currentTimeMillis();
        long timeElapsedMultipleAlg = endTimeMultipleAlg - startTimeMultipleAlg;

        LOGGER.info("single thread " + timeElapsedSingleAlg + " mills");
        LOGGER.info("multi thread " + timeElapsedMultipleAlg + " mills");

        assertTrue(timeElapsedMultipleAlg < timeElapsedSingleAlg);
    }
}
