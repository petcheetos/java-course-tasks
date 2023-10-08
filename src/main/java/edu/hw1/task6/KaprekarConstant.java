package edu.hw1.task6;

import java.util.Arrays;
import java.util.Collections;

public class KaprekarConstant {
    public static int CONST = 6174;
    public static int NUMBER_BASE = 10;

    private KaprekarConstant() {
    }

    public static int countK(int num) {
        if (num <= 1000 || num > 9999) {
            return -1;
        }
        int countSteps = 0;
        Integer[] digitsInOrder = {0, 0, 0, 0};
        Integer[] digitsReverseOrder = {0, 0, 0, 0};

        for (int i = 0; num > 0; i++) {
            digitsInOrder[i] = num % NUMBER_BASE;
            digitsReverseOrder[i] = num % NUMBER_BASE;
            num /= NUMBER_BASE;
        }
        Arrays.sort(digitsInOrder);
        Arrays.sort(digitsReverseOrder, Collections.reverseOrder());
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
        } else if (sum == CONST) {
            return ++countSteps;
        }
        return 1 + countK(sum);
    }
}
