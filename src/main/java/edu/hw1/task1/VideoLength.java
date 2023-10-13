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
        int minutes, seconds;
        try {
            minutes = Integer.parseInt(numbers[0]);
            seconds = Integer.parseInt(numbers[1]);
        }
        catch (NumberFormatException e) {
            return -1;
        }
        if (seconds >= SECONDS_IN_MINUTE || seconds < 0 || minutes < 0) {
            return -1;
        }
        if (Integer.MAX_VALUE / SECONDS_IN_MINUTE <= minutes) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
