package edu.hw1.task3;

import java.util.Arrays;

public final class ArrayFunctions {
    private ArrayFunctions() {
    }

    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        if (firstArray == null || secondArray == null || firstArray.length == 0 || secondArray.length == 0) {
            return false;
        }
        int minElemInFirst = Arrays.stream(firstArray).min().getAsInt();
        int minElemInSecond = Arrays.stream(secondArray).min().getAsInt();
        int maxElemInFirst = Arrays.stream(firstArray).max().getAsInt();
        int maxElemInSecond = Arrays.stream(secondArray).max().getAsInt();

        return (minElemInFirst > minElemInSecond) && (maxElemInFirst < maxElemInSecond);
    }
}
