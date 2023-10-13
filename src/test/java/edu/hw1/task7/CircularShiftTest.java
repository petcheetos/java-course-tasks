package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularShiftTest {
    @DisplayName("Testing leftShift()")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "2147483647, 0, 2147483647",
        "2147483647, 2, -1",
        "13, -1, -1",
        "14, 2, 11",
        "8, 3, 4"})
    @ParameterizedTest
    void testLeftShift(int num, int shift, int expected) {
        assertEquals(CircularShift.rotateLeft(num, shift), expected);
    }

    @DisplayName("Testing rightShift()")
    @CsvSource({
        "8, 1, 4",
        "9, 2, 6",
        "16, 3, 2",
        "2147483647, 2, -1",
        "-2147483648, 5, -1",
        "14, 2, 11",
        "-13, 5, -1"})
    @ParameterizedTest
    void testRightShift(int num, int shift, int expected) {
        assertEquals(CircularShift.rotateRight(num, shift), expected);
    }
}
