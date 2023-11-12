package edu.hw5.task1;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComputerClubAnalyticsTest {

    @Test
    void testGetAverageTimeOfSessions_1() {
        ArrayList<String> sessions = new ArrayList<>(List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        ));
        assertEquals("3ч 40м", ComputerClubAnalytics.getAverageTimeOfSessions(sessions));
    }

    @Test
    void testGetAverageTimeOfSessions_2() {
        ArrayList<String> sessions = new ArrayList<>(List.of(
            "2022-03-23, 10:20 - 2022-03-23, 23:50",
            "2022-03-12, 00:20 - 2022-03-12, 15:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        ));
        assertEquals("10ч 56м", ComputerClubAnalytics.getAverageTimeOfSessions(sessions));
    }

    @Test
    void testGetAverageTimeOfSessionsWithEmptyList() {
        ArrayList<String> sessions = new ArrayList<>(List.of());
        assertEquals("0ч 00м", ComputerClubAnalytics.getAverageTimeOfSessions(sessions));
    }

    @Test
    void testGetAverageTimeOfSessionsWithNull() {
        assertThrows(
            NullPointerException.class,
            () -> ComputerClubAnalytics.getAverageTimeOfSessions(null)
        );
    }
}
