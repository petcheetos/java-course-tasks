package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class VideoLengthTest {
    @DisplayName("Testing minutesToSecond()")
    @CsvSource({
        "01:00, 60",
        "13:56, 836",
        "10:60, -1",
        "999:54, 59994",
        "a:54, -1",
        "-10:40, -1",
        ", -1"})
    @ParameterizedTest
    void testWithCsvSource(String testStr, int expectedResult) {
        assertEquals(VideoLength.minutesToSecond(testStr), expectedResult);
    }
}
