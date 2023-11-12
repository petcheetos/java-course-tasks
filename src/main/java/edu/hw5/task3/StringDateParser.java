package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class StringDateParser extends DateParseExecutor {

    @Override
    public Optional<LocalDate> parse(String string) {
        LocalDate date;
        if (string.equals("tomorrow")) {
            date = LocalDate.now().plusDays(1);
        } else if (string.equals("today")) {
            date = LocalDate.now();
        } else if (string.equals("yesterday")) {
            date = LocalDate.now().minusDays(1);
        } else {
            return parseNext(string);
        }
        return Optional.of(date);
    }
}
