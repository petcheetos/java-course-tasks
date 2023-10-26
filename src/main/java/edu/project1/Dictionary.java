package edu.project1;

import java.util.Random;

public final class Dictionary {
    static final Random RANDOM = new Random();
    static String[] dictionary = new String[] {"cat", "whale", "apple", "peach", "football"};

    private Dictionary() {
    }

    public static String choseRandomWord() {
        return dictionary[RANDOM.nextInt(dictionary.length + 1)];
    }
}
