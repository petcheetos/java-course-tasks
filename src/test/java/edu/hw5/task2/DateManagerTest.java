package edu.hw5.task2;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateManagerTest {

    @Test
    void testFindNearestFriday13() {
        assertEquals(List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        ), DateManager.getFridays13(1925));

        assertEquals(List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        ), DateManager.getFridays13(2024));

        assertThrows(
            IllegalArgumentException.class,
            () -> DateManager.getFridays13(0)
        );
    }

    @Test
    void testGetFridays13() {
        assertEquals(
            LocalDate.of(2024, 9, 13),
            DateManager.findNearestFriday13(LocalDate.of(2024, 1, 1))
        );
        assertEquals(
            LocalDate.of(2024, 12, 13),
            DateManager.findNearestFriday13(LocalDate.of(2024, 12, 1))
        );
    }
}
