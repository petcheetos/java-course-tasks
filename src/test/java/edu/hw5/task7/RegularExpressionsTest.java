package edu.hw5.task7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularExpressionsTest {
    @CsvSource({
        "1101, true",
        "110, true",
        "00011111, true",
        "00001, true",
        "000, true",
        "111, false",
        "00, false",
        "00101, false",
        "00002, false",})
    @ParameterizedTest
    void testIsContainsNotLess3SymbolsAndThirdIsZero(String string, boolean expected) {
        assertEquals(RegularExpressions.isContainsNotLess3SymbolsAndThirdIsZero(string), expected);
    }

    @CsvSource({
        "0110, true",
        "0110, true",
        "0000, true",
        "0000, true",
        "11, true",
        "100, false",
        "10, false",
        "0100001, false",
        "1000021, false",})
    @ParameterizedTest
    void testIsStartEndWithSameSymbol(String string, boolean expected) {
        assertEquals(RegularExpressions.isStartEndWithSameSymbol(string), expected);
    }

    @CsvSource({
        "000, true",
        "10, true",
        "1, true",
        "101, true",
        "1010, false",
        "112120, false",
        "2, false",})
    @ParameterizedTest
    void testIsLengthBetweenOneAndThree(String string, boolean expected) {
        assertEquals(RegularExpressions.isLengthBetweenOneAndThree(string), expected);
    }
}
