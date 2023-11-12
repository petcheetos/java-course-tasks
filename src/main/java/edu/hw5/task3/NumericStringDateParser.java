package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericStringDateParser extends DateParseExecutor {
    public final static Pattern PATTERN = Pattern.compile("^([0-9])+ days ago$");

    @Override
    public Optional<LocalDate> parse(String string) {
        LocalDate date;
        if (string.equals("1 day ago")) {
            date = LocalDate.now().minusDays(1);
        } else {
            Matcher matcher = PATTERN.matcher(string);
            if (matcher.matches()) {
                long days = Long.parseLong(string.split(" ")[0]);
                date = LocalDate.now().minusDays(days);

            } else {
                return parseNext(string);
            }
        }
        return Optional.of(date);
    }
}
