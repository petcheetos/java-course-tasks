package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserTest {

    @Test
    void testParseDate() {
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), DateParser.parseDate("2020-10-10"));
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), DateParser.parseDate("2020-12-2"));
        assertEquals(Optional.of(LocalDate.of(1976, 3, 1)), DateParser.parseDate("1/3/1976"));
        assertEquals(Optional.of(LocalDate.of(2020, 3, 1)), DateParser.parseDate("1/3/20"));
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), DateParser.parseDate("tomorrow"));
        assertEquals(Optional.of(LocalDate.now()), DateParser.parseDate("today"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), DateParser.parseDate("yesterday"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), DateParser.parseDate("1 day ago"));
        assertEquals(Optional.of(LocalDate.now().minusDays(2234)), DateParser.parseDate("2234 days ago"));

        assertEquals(Optional.empty(), DateParser.parseDate("the 22th of March"));

        assertThrows(
            IllegalArgumentException.class,
            () -> DateParser.parseDate("")
        );
    }
}
