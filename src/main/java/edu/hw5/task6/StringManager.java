package edu.hw5.task6;

import java.util.regex.Pattern;

public class StringManager {

    private StringManager() {
    }

    public static boolean isSubsequence(String sub, String string) {
        Pattern pattern = Pattern.compile(sub);
        return pattern.matcher(string).find();
    }
}
