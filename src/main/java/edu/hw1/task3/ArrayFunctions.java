package edu.hw1.task3;

import java.util.Arrays;

public final class ArrayFunctions {
    private ArrayFunctions() {
    }

    public static boolean isNestable(int[] firstArr, int[] secondArr) {
        if (firstArr == null || secondArr == null || firstArr.length == 0 || secondArr.length == 0) {
            return false;
        }
        int minElemInFirst = Arrays.stream(firstArr).min().getAsInt();
        int minElemInSecond = Arrays.stream(secondArr).min().getAsInt();
        int maxElemInFirst = Arrays.stream(firstArr).max().getAsInt();
        int maxElemInSecond = Arrays.stream(secondArr).max().getAsInt();

        return (minElemInFirst > minElemInSecond) && (maxElemInFirst < maxElemInSecond);
    }
}
