package edu.hw7.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorPiTest {

    @Test
    void testSingleThreadAlgorithm() {
        double result = CalculatorPi.doSingleThreadAlgorithm(100000);
        assertTrue(3.13D < result && result < 3.15D);
    }

    @Test
    void testMultipleThreadsAlgorithm() {
        double result = CalculatorPi.doMultipleThreadsAlgorithm(1000000, 8);
        assertTrue(3.13D < result && result < 3.15D);
    }
}