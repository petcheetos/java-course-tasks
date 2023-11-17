package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParseExecutor {
    private DateParseExecutor nextParser;

    public void setNextParser(DateParseExecutor nextParser) {
        this.nextParser = nextParser;
    }

    public Optional<LocalDate> parseNext(String string) {
        if (nextParser == null) {
            return Optional.empty();
        }
        return nextParser.parse(string);
    }

    public abstract Optional<LocalDate> parse(String string);
}
