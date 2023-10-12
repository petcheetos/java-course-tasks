package edu.hw1.task2;

public final class Numbers {
    public final static int DIVISIOR = 10;

    private Numbers() {
    }

    public static int countDigits(int number) {
        int num = Math.abs(number);
        int count = 1;
        while (num > DIVISIOR) {
            count++;
            num = num / DIVISIOR;
        }
        return count;
    }
}