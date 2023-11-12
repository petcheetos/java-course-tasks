package edu.hw5.task5;

import java.util.regex.Pattern;

public class CarNumberValidator {
    public static final String ALLOWED_LETTERS = "ABEKMNOPCTYX";

    private CarNumberValidator() {
    }

    public static boolean isNumberCorrect(String number) {
        Pattern pattern = Pattern.compile("^[" + ALLOWED_LETTERS + "]\\d{3}[" + ALLOWED_LETTERS + "]{2}\\d{3}$");
        return pattern.matcher(number).matches();
    }
}
