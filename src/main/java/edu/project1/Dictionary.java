package edu.project1;

import java.util.Random;

public final class Dictionary {
    static String[] dictionary = new String[] {"cat", "whale", "apple", "peach", "football"};

    private Dictionary() {
    }

    public static String choseRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(dictionary.length);
        return dictionary[randomIndex];
    }
}
