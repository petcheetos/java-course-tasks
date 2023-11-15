package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DateManager {
    public static final int THIRTEEN = 13;

    private DateManager() {
    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> getFridays13(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException();
        }
        List<LocalDate> fridays13 = new ArrayList<>();
        LocalDate firstDay = LocalDate.of(year, 1, 13);
        LocalDate lastDay = LocalDate.of(year, 12, 31);
        while (firstDay.isBefore(lastDay)) {
            if (firstDay.getDayOfWeek() == DayOfWeek.FRIDAY && firstDay.getDayOfMonth() == THIRTEEN) {
                fridays13.add(firstDay);
            }
            firstDay = firstDay.plusMonths(1);
        }
        return fridays13;
    }

    public static LocalDate findNearestFriday13(LocalDate currentDate) {
        LocalDate nextFriday13 = currentDate;
        while (!(nextFriday13.getDayOfWeek() == DayOfWeek.FRIDAY && nextFriday13.getDayOfMonth() == THIRTEEN)) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday13;
    }
}
