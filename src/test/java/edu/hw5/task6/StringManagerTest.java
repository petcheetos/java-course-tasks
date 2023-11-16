package edu.hw5.task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringManagerTest {

    @CsvSource({
        "abc, achfdbaabgabcaabg, true",
        "123, 789465123, true",
        "cat, abdcdef, false",
        "f\\d, assf\\daasda, true",
        "kit, kit, true",})
    @ParameterizedTest
    void testIsSubsequence(String sub, String string, boolean expected) {
        assertEquals(StringManager.isSubsequence(sub, string), expected);
    }
}
