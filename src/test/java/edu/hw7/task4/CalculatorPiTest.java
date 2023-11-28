package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorPiTest {
    private final Logger LOGGER = LogManager.getLogger();

    @Test
    void testSingleThreadAlgorithm() {
        double result = CalculatorPi.doSingleThreadAlgorithm(1_000_000);
        assertTrue(3.12D < result && result < 3.16D);
        LOGGER.info("single thread accuracy = " + abs(Math.PI - result));
    }

    @Test
    void testMultipleThreadsAlgorithm() {
        double result = CalculatorPi.doMultipleThreadsAlgorithm(1_000_000, 8);
        assertTrue(3.12D < result && result < 3.16D);
        LOGGER.info("multi thread accuracy = " + abs(Math.PI - result));
    }

    @Test
    void performanceTest() {
        long startTimeSingleAlg = System.currentTimeMillis();
        CalculatorPi.doSingleThreadAlgorithm(1_000_000_000);
        long endTimeSingleAlg = System.currentTimeMillis();
        long timeElapsedSingleAlg = endTimeSingleAlg - startTimeSingleAlg;

        long startTimeMultipleAlg = System.currentTimeMillis();
        CalculatorPi.doMultipleThreadsAlgorithm(1_000_000_000, 8);
        long endTimeMultipleAlg = System.currentTimeMillis();
        long timeElapsedMultipleAlg = endTimeMultipleAlg - startTimeMultipleAlg;

        LOGGER.info("single thread " + timeElapsedSingleAlg + " mills");
        LOGGER.info("multi thread " + timeElapsedMultipleAlg + " mills");

        assertTrue(timeElapsedMultipleAlg < timeElapsedSingleAlg);
    }
}
