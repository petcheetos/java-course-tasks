package edu.hw5.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarNumberValidatorTest {

    @CsvSource({
        "А123ВЕ777, true",
        "О777ОО177, true",
        "123АВЕ777, false",
        "А123ВЕ7777, false",
        "А123ВГ77, false"})
    @ParameterizedTest
    void testCheckNumber(String str, boolean expected) {
        assertEquals(CarNumberValidator.isNumberCorrect(str), expected);
    }
}
