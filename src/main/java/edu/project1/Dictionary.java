package edu.project1;

public final class Dictionary {
    private static final int DEFAULT_SIZE = 5;
    static String[] dictionary = new String[] {"cat", "whale", "apple", "peach", "football"};

    private Dictionary() {
    }

    public static String choseRandomWord() {
        int index = (int) (Math.random() * DEFAULT_SIZE);
        return dictionary[index];
    }
}
