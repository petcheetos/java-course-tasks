package edu.hw3.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsTest {
    @DisplayName("Testing convertToRoman")
    @CsvSource({
        "2, II",
        "12, XII",
        "16, XVI",
        "1892, MDCCCXCII",
        "90, XC",
        "45, XLV"})
    @ParameterizedTest
    void testWithCsvSource(int num, String expectedResult) {
        assertEquals(RomanNumerals.convertToRoman(num), expectedResult);
    }

    @Test
    void testExpectedException() {
        assertThatThrownBy(() -> RomanNumerals.convertToRoman(5433))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RomanNumerals.convertToRoman(-10))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
