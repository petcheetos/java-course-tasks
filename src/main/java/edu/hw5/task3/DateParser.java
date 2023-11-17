package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private DateParser() {}

    public static Optional<LocalDate> parseDate(String string) {

        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Date must be not empty and not null");
        }

        DashNumericDateParser dashNumericDateParser = new DashNumericDateParser();
        AnotherDashNumericDateParser anotherDashNumericDateParser = new AnotherDashNumericDateParser();
        SlashNumericDateParser slashNumericDateParser = new SlashNumericDateParser();
        AnotherSlashNumericDateParser anotherSlashNumericDateParser = new AnotherSlashNumericDateParser();
        NumericStringDateParser numericStringDateParser = new NumericStringDateParser();
        StringDateParser stringDateParser = new StringDateParser();

        dashNumericDateParser.setNextParser(anotherDashNumericDateParser);
        anotherDashNumericDateParser.setNextParser(slashNumericDateParser);
        slashNumericDateParser.setNextParser(anotherSlashNumericDateParser);
        anotherSlashNumericDateParser.setNextParser(numericStringDateParser);
        numericStringDateParser.setNextParser(stringDateParser);
        stringDateParser.setNextParser(null);

        return dashNumericDateParser.parse(string);
    }
}
