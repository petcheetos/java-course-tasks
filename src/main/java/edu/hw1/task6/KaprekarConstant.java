package edu.hw1.task6;

import java.util.Arrays;
import java.util.Collections;

public class KaprekarConstant {
    public static final int KAPREKAR_CONST = 6174;
    public static final int NUMBER_BASE = 10;

    private KaprekarConstant() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int num) {
        if (num <= 1000 || num > 9999) {
            return -1;
        }
        int countSteps = 0;
        int[] digitsInOrder = {0, 0, 0, 0};
        int[] digitsReverseOrder = {0, 0, 0, 0};
        int number = num;
        for (int i = 0; number > 0; i++) {
            digitsInOrder[i] = number % NUMBER_BASE;
            number /= NUMBER_BASE;
        }
        Arrays.sort(digitsInOrder);
        for (int i = 0; i < digitsReverseOrder.length ; i++) {
            digitsReverseOrder[i] = digitsInOrder[digitsInOrder.length - 1 - i];
        }
        int leftNum = 0;
        int rightNum = 0;
        int multiplier = 1;
        for (int i = 3; i >= 0; i--) {
            leftNum = multiplier * leftNum + digitsInOrder[i];
            rightNum = multiplier * rightNum + digitsReverseOrder[i];
            multiplier = NUMBER_BASE;
        }
        int sum = leftNum - rightNum;
        if (sum == 0) {
            return -1;
        } else if (sum == KAPREKAR_CONST) {
            return ++countSteps;
        }
        return 1 + countK(sum);
    }
}
