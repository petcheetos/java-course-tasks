package edu.hw1.task4;

public final class StringFunctions {
    private StringFunctions() {
    }

    public static String fixString(String str) {
        if (!str.isEmpty()) {
            char[] symbols = str.toCharArray();
            for (int i = 0; i < str.length() - str.length() % 2; i += 2) {
                char temp = symbols[i];
                symbols[i] = symbols[i + 1];
                symbols[i + 1] = temp;
            }
            return new String(symbols);
        }
        return "";
    }
}
