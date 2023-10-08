package edu.hw1.task7;

public final class CircularShift {
    public static final int NUMBER_BASE = 2;

    private CircularShift() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        int binaryFormLength = Integer.toBinaryString(n).length();
        int newShift = shift % binaryFormLength;
        return ((n << newShift) | (n >> (binaryFormLength - newShift)))
            % (int) Math.pow(NUMBER_BASE, binaryFormLength);
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        int binaryFormLength = Integer.toBinaryString(n).length();
        int newShift = shift % binaryFormLength;
        return ((n >> newShift) | (n << (binaryFormLength - newShift)))
            % (int) Math.pow(NUMBER_BASE, binaryFormLength);
    }
}
