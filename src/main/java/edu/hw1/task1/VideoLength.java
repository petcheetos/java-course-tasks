package edu.hw1.task1;

public final class VideoLength {
    private final static int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
    }

    public static int minutesToSecond(String string) {
        if (string == null) {
            return -1;
        }
        String[] numbers = string.split(":");
        for (var str : numbers) {
            if (!str.matches("[-+]?\\d+")) {
                return -1;
            }
        }
        int minutes = Integer.parseInt(numbers[0]);
        int seconds = Integer.parseInt(numbers[1]);
        if (seconds >= SECONDS_IN_MINUTE || seconds < 0 || minutes < 0) {
            return -1;
        }
        if (Integer.MAX_VALUE / SECONDS_IN_MINUTE <= minutes) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
