package edu.hw1.task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaprekarConstantTest {
    @CsvSource({
        "3524, true, 3",
        "6621,true, 5",
        "6554,true, 4",
        "1234, true, 3",
        "2050, true, 7",
        "1112, true, 5",
        "986, true, -1",
        "1000,true, -1",
        "5555, true,-1",
        "10999, true, -1"})
    @ParameterizedTest
    void testWithCsvSource(int num, boolean isFirstIteration, int expected) {
        assertEquals(KaprekarConstant.countK(num, isFirstIteration), expected);
    }
}
