package edu.hw1.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringFunctionsTest {
    @DisplayName("Testing fixString()")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "'', ''"})
    @ParameterizedTest
    void testWithCsvSource(String str, String expectedStr) {
        assertEquals(StringFunctions.fixString(str), expectedStr);
    }
}
