package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @CsvSource({
        "5, 120",
        "7, 5040",
        "1, 1",
        "0, 1"
    })
    @ParameterizedTest
    void testCalculate(int num, int expected) {
        assertEquals(expected, Factorial.calculate(num));
    }

    @Test
    void testCalculateThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Factorial.calculate(-10)
        );
    }
}
