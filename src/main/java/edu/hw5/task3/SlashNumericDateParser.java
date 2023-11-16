package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SlashNumericDateParser extends DateParseExecutor {
    public final static DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("d/M/yyyy");

    @Override
    public Optional<LocalDate> parse(String string) {
        try {
            LocalDate date = LocalDate.parse(string, PATTERN);
            return Optional.of(date);
        } catch (DateTimeParseException ex) {
            return parseNext(string);
        }
    }
}
