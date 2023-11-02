package edu.hw3.task1;

public class AtbashCode {
    private static final int DECIMAL_UPPERCASE_A = 65;
    private static final int DECIMAL_UPPERCASE_Z = 90;
    private static final int DECIMAL_LOWERCASE_A = 97;
    private static final int DECIMAL_LOWERCASE_Z = 122;

    private AtbashCode() {
    }

    public static String atbash(String string) {
        if (string.isEmpty()) {
            throw new NullPointerException("String must be not null");
        }
        StringBuilder codedString = new StringBuilder(string);
        for (int i = 0; i < codedString.length(); i++) {
            int currentSymbol = codedString.charAt(i);

            if (currentSymbol >= DECIMAL_UPPERCASE_A && currentSymbol <= DECIMAL_UPPERCASE_Z) {
                codedString.setCharAt(i, (char) (DECIMAL_UPPERCASE_Z - (currentSymbol - DECIMAL_UPPERCASE_A)));
            } else if (currentSymbol >= DECIMAL_LOWERCASE_A && currentSymbol <= DECIMAL_LOWERCASE_Z) {
                codedString.setCharAt(i, (char) (DECIMAL_LOWERCASE_Z - (currentSymbol - DECIMAL_LOWERCASE_A)));
            }
        }
        return codedString.toString();
    }
}
