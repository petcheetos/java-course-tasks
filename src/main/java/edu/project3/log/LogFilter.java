package edu.project3.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class LogFilter {
    private static final int MAX_HOUR = 23;
    private static final int MAX_MIN = 59;
    private static final int MAX_SECOND = 59;

    private LogFilter() {
    }

    public static List<Log> filterLogsByDateRange(LocalDate startDate, LocalDate endDate, List<Log> logs) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(MAX_HOUR, MAX_MIN, MAX_SECOND);

        return logs.stream()
            .filter(log -> log.time().isAfter(startDateTime) && log.time().isBefore(endDateTime))
            .collect(Collectors.toList());
    }

    public static List<Log> filterLogsAfterDate(LocalDate startDate, List<Log> logs) {
        LocalDateTime startDateTime = startDate.atStartOfDay();

        return logs.stream()
            .filter(log -> log.time().isAfter(startDateTime))
            .collect(Collectors.toList());
    }

    public static List<Log> filterLogsBeforeDate(LocalDate endDate, List<Log> logs) {
        LocalDateTime endDateTime = endDate.atTime(MAX_HOUR, MAX_MIN, MAX_SECOND);

        return logs.stream()
            .filter(log -> log.time().isBefore(endDateTime))
            .collect(Collectors.toList());
    }
}
