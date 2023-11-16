package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ComputerClubAnalytics {
    public final static DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private ComputerClubAnalytics() {
    }

    public static String getAverageTimeOfSessions(ArrayList<String> sessions) {
        if (sessions == null) {
            throw new NullPointerException();
        }
        if (sessions.isEmpty()) {
            return "0ч 00м";
        }

        Duration totalMinutes = Duration.ZERO;
        for (String session : sessions) {
            String[] splitStr = session.split(" - ");
            Duration duration = Duration.between(
                LocalDateTime.parse(splitStr[0], PATTERN), LocalDateTime.parse(splitStr[1], PATTERN)
            );
            totalMinutes = totalMinutes.plusMinutes(duration.toMinutes());
        }
        Duration averageTime = totalMinutes.dividedBy(sessions.size());
        return averageTime.toHours() + "ч" + " " + averageTime.toMinutesPart() + "м";
    }
}
