package edu.hw5.task8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtraRegularExpressionsTest {

    @CsvSource({
        "110, true",
        "00011, true",
        "0, true",
        "1110, false",
        "00, false",
        "00002, false",})
    @ParameterizedTest
    void testIsOddLength(String string, boolean expected) {
        assertEquals(ExtraRegularExpression.isOddLength(string), expected);
    }

    @CsvSource({
        "110, true",
        "1110, true",
        "010101, true",
        "00110, true",
        "00111, true",
        "111, false",
        "11, false",
        "1000021, false",})
    @ParameterizedTest
    void testIsNot11Or111(String string, boolean expected) {
        assertEquals(ExtraRegularExpression.isNot11Or111(string), expected);
    }

    @CsvSource({
        "010, true",
        "01001, true",
        "0, true",
        "1001, true",
        "011, false",
        "100110, false",
        "11000, false",
        "1000021, false",})
    @ParameterizedTest
    void testIsNotConsecutiveOnes(String string, boolean expected) {
        assertEquals(ExtraRegularExpression.isNotConsecutiveOnes(string), expected);
    }

    @CsvSource({
        "10101, true",
        "1010, true",
        "1, true",
        "11111, true",
        "011, false",
        "100110, false",
        "11000, false",
        "1000021, false",})
    @ParameterizedTest
    void testIsEachOddSymbolOne(String string, boolean expected) {
        assertEquals(ExtraRegularExpression.isEachOddSymbolOne(string), expected);
    }
}
