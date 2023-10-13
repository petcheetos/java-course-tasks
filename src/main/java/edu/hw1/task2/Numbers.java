package edu.hw1.task2;

public final class Numbers {
    public final static int DIVISOR = 10;

    private Numbers() {
    }

    public static int countDigits(int number) {
        int result = 0;
        int num = number;
        if (num == 0) {
            return 1;
        }
        while (num != 0) {
            result++;
            num /= DIVISOR;
        }
        return result;
    }
}
