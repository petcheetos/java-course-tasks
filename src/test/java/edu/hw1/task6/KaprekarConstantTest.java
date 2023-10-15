package edu.hw1.task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaprekarConstantTest {
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "2050, 7",
        "1112, 5",
        "986, -1",
        "6174, 0",
        "1000, -1",
        "5555, -1",
        "10999, -1"})
    @ParameterizedTest
    void testWithCsvSource(int num, int expected) {
        assertEquals(KaprekarConstant.countK(num), expected);
    }
}
