package edu.hw1.task5;

public final class Palindrome {
    static final int NUMBER_BASE = 10;

    private Palindrome() {
    }

    public static boolean isPalindromeDescendant(int num) {
        if (num < NUMBER_BASE) {
            return false;
        }

        int reversedNum = 0;
        int temp = num;
        while (temp > 0) {
            reversedNum = reversedNum * NUMBER_BASE + temp % NUMBER_BASE;
            temp /= NUMBER_BASE;
        }
        if (reversedNum == num) {
            return true;
        }

        int newNum = 0;
        int multiplier = 1;
        while (reversedNum > 0) {
            int last = reversedNum % NUMBER_BASE;
            int prev = (reversedNum / NUMBER_BASE) % NUMBER_BASE;
            newNum = multiplier * newNum + (prev + last);
            multiplier = NUMBER_BASE;
            reversedNum /= NUMBER_BASE * NUMBER_BASE;
        }

        return isPalindromeDescendant(newNum);
    }
}
