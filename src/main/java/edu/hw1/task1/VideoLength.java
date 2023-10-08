package edu.hw1.task1;

public final class VideoLength {
    private final static int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
    }

    public static int minutesToSecond(String str) {
        if (str == null) {
            return -1;
        }
        String[] numbers = str.split(":");
        int minutes = Integer.parseInt(numbers[0]);
        int seconds = Integer.parseInt(numbers[1]);
        if (seconds >= SECONDS_IN_MINUTE || seconds < 0 || minutes < 0) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
