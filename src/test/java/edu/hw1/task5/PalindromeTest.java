package edu.hw1.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PalindromeTest {
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "5, false",
        "768, false"})
    @ParameterizedTest
    void testWithCsvSource(int num, boolean expected) {
        assertEquals(Palindrome.isPalindromeDescendant(num), expected);
    }
}
