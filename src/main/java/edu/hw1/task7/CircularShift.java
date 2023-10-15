package edu.hw1.task7;

public final class CircularShift {
    public static final int NUMBER_BASE = 2;

    private CircularShift() {
    }

    public static int rotateLeft(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (shift == 0) {
            return n;
        }
        int binaryFormLength = Integer.toBinaryString(n).length();
        int newShift = shift % binaryFormLength;

        if (Integer.MAX_VALUE / newShift <= n) {
            return -1;
        }
        int numShiftLeft = n << newShift;
        int numShiftRight = n >> (binaryFormLength - newShift);
        return (numShiftLeft | numShiftRight) % (int) Math.pow(NUMBER_BASE, binaryFormLength);
    }

    public static int rotateRight(int n, int shift) {
        if (n < 0 || shift < 0) {
            return -1;
        }
        if (shift == 0) {
            return n;
        }
        int binaryFormLength = Integer.toBinaryString(n).length();
        int newShift = shift % binaryFormLength;
        int numShiftRight = n >> newShift;

        if (Integer.MAX_VALUE / (binaryFormLength - newShift) <= n) {
            return -1;
        }
        int numShiftLeft = n << (binaryFormLength - newShift);
        return (numShiftRight | numShiftLeft) % (int) Math.pow(NUMBER_BASE, binaryFormLength);
    }
}
