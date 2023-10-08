package edu.hw1.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class NumbersTest {
    @DisplayName("Testing countDigits()")
    @CsvSource({
        "4666, 4",
        "544, 3",
        "0, 1",
        "-754, 3"})
    @ParameterizedTest
    void testWithCsvSource(int num, int expectedResult) {
        assertEquals(Numbers.countDigits(num), expectedResult);
    }
}
